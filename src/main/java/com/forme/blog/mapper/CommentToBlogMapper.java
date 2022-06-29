package com.forme.blog.mapper;


import com.forme.blog.model.entity.Comment;
import com.forme.blog.model.entity.CommentToBlog;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LiQuan
 * @since 2022-06-09
 */
public interface CommentToBlogMapper  {

    List<Comment> selectByBlogId(Integer blogId);

    int insert(CommentToBlog commentToBlog);

    int deleteByCommentId(Integer commentId);

    int deleteByBlogId(Integer blogId);

}
