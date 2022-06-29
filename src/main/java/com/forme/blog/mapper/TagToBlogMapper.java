package com.forme.blog.mapper;


import com.forme.blog.model.entity.Tag;
import com.forme.blog.model.entity.TagToBlog;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liQuan
 * @since 2022-06-09
 */
public interface TagToBlogMapper {

    List<Tag> selectByBlogId(Integer blogId);

    int insert(TagToBlog tagToBlog);

    int delete(TagToBlog tagToBlog);

    int deleteById(Integer tagId);

}
