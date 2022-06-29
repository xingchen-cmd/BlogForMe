package com.forme.blog.mapper;


import com.forme.blog.model.entity.Blog;
import com.forme.blog.model.entity.BlogDraft;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liQuan
 * @since 2022-06-09
 */
public interface BlogDraftMapper {
    List<BlogDraft> selectAll();

    BlogDraft selectByPrimaryKey(Integer blogDraftId);

    List<BlogDraft> selectByTitle(String key);

    List<BlogDraft> selectByContent(String key);

    int updateSelective(BlogDraft blogDraft);

    int insertSelective(BlogDraft blogDraft);

    int delete(Integer id);

}
