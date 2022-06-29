package com.forme.blog.mapper;


import com.forme.blog.model.entity.Classify;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LiQuan
 * @since 2022-06-09
 */
public interface ClassifyMapper{
    List<Classify> selectAll();

    Classify selectByPrimaryKey(Integer classifyId);

    Integer selectByClassifyName(String classifyName);

    int update(Classify classify);

    int insert(Classify classify);

    int delete(Integer classifyId);





}
