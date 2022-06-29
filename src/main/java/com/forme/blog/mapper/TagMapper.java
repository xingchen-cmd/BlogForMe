package com.forme.blog.mapper;


import com.forme.blog.model.entity.Tag;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LiQuan
 * @since 2022-06-09
 */
public interface TagMapper {
    List<Tag> selectAll();

    Integer selectByName(String tagName);

    Tag selectById(Integer tagId);

    int insert(Tag tag);

    int update(Tag tag);

    int delete(Integer id);

}
