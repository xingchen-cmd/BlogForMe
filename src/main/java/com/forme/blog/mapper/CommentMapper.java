package com.forme.blog.mapper;


import com.forme.blog.model.entity.Comment;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LiQuan
 * @since 2022-06-09
 */
public interface CommentMapper {


    List<Comment> selectByRootId(Integer rootId);

    List<Comment> selectByParentId(Integer parentId);

    Comment selectByCommentId(Integer commentId);

    int insertSelective(Comment comment);

    int delete(Integer commentId);





}
