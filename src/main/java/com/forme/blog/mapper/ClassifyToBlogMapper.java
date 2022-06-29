package com.forme.blog.mapper;


import com.forme.blog.model.entity.Blog;
import com.forme.blog.model.entity.ClassifyToBlog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LiQuan
 * @since 2022-06-09
 */
public interface ClassifyToBlogMapper {

        List<Blog> selectByClassifyId(Integer classifyId);

        ClassifyToBlog selectByBlogIdAndClassifyId(@Param("blogId") Integer blogId, @Param("classifyId") Integer classifyId);

        int insert(ClassifyToBlog classifyToBlog);

        int delete(ClassifyToBlog classifyToBlog);

        int deleteById(Integer classifyId);
}
