package com.forme.blog.service.impl;

import com.forme.blog.common.ResultCode;
import com.forme.blog.common.ResultResponse;
import com.forme.blog.dto.CommentParam;
import com.forme.blog.mapper.CommentMapper;
import com.forme.blog.mapper.CommentToBlogMapper;
import com.forme.blog.model.entity.Comment;
import com.forme.blog.model.entity.CommentToBlog;
import com.forme.blog.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Autowired
    CommentToBlogMapper commentToBlogMapper;

    @Override
    public ResultResponse getParentComment(int blogId, int page, int sizeLimit) {
        PageHelper.startPage(page,sizeLimit);
        List<Comment> rootParentComment=commentToBlogMapper.selectByBlogId(blogId);
        return ResultResponse.success(new PageInfo<>(rootParentComment));
    }

    @Override
    public ResultResponse getSonComment(int blogId, int parentId, int page, int sizeLimit) {
        PageHelper.startPage(page,sizeLimit);
        List<Comment> sonComment=commentToBlogMapper.selectByBlogIdAndRootCommentId(blogId,parentId);
        return ResultResponse.success(new PageInfo<>(sonComment));
    }

    @Override
    public ResultResponse addComment(int blogId, CommentParam commentParam) {
        Comment comment=new Comment();
        BeanUtils.copyProperties(commentParam,comment);
        if(Objects.isNull(comment.getCommentDate())){
            comment.setCommentDate(LocalDateTime.now());
        }

        commentMapper.insertSelective(comment);
        Integer commentId=comment.getCommentId();
        CommentToBlog commentToBlog=new CommentToBlog();
        commentToBlog.setBlogId(blogId);
        commentToBlog.setCommentId(commentId);
        commentToBlogMapper.insert(commentToBlog);
        return ResultResponse.success(null);
    }

    /**
     * 删除评论
     * @param blogId
     * @param commentId
     * @return 删除成功
     */
    @Override
    public ResultResponse deleteComment(int blogId, int commentId) {
        Comment comment=commentMapper.selectByCommentId(commentId);
        CommentToBlog commentToBlog=commentToBlogMapper.selectByCommentId(commentId);
        if(Objects.isNull(comment)||Objects.isNull(commentToBlog))return ResultResponse.failed(ResultCode.COMMENT_IS_DELETE);
        List<Comment> commentList=commentMapper.selectByRootId(commentId);
        commentMapper.delete(commentId);
        commentToBlogMapper.deleteByCommentId(commentId);
        List<Integer> commentIdList=commentList.stream().map(a->a.getCommentId()).toList();
        for(int id:commentIdList){
            commentMapper.delete(id);
            commentToBlogMapper.deleteByCommentId(commentId);
        }
        return ResultResponse.success(null);
    }

    @Override
    public ResultResponse getComment(int commentId) {
        Comment comment=commentMapper.selectByCommentId(commentId);
        return ResultResponse.success(comment);
    }
}
