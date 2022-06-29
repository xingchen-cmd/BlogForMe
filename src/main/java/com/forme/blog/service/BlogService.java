package com.forme.blog.service;

import com.forme.blog.common.ResultResponse;
import com.forme.blog.dto.BlogParam;
import com.forme.blog.dto.ClassifyParam;
import com.forme.blog.model.entity.Classify;
import com.forme.blog.model.entity.Tag;

import java.util.List;

public interface BlogService {

    ResultResponse postBlog(BlogParam blogParam);

    ResultResponse addTag(Tag tag);

    ResultResponse deleteTag(Integer tagId);

    ResultResponse addClassify(Classify classify);

    ResultResponse updateClassify(ClassifyParam classify);

    ResultResponse deleteClassify(Integer classifyId);

    ResultResponse addBlogToClassify(Integer classifyId, List<Integer> blogIdList);
}
