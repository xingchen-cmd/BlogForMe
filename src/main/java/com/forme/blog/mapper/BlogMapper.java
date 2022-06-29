package com.forme.blog.mapper;


import com.forme.blog.model.entity.Blog;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LiQuan
 * @since 2022-06-09
 */
public interface BlogMapper {
    List<Blog> selectAll();

    Blog selectByPrimaryKey(Integer id);

    List<Blog> selectByTitle(String key);

    List<Blog> selectByContent(String key);

    int updateSelective(Blog blog);

    int insertSelective(Blog blog);

    int delete(Integer id);


}
