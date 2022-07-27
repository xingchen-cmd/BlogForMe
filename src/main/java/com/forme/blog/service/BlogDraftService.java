package com.forme.blog.service;

import com.forme.blog.common.ResultResponse;
import com.forme.blog.dto.BlogDraftParam;

public interface BlogDraftService {


    ResultResponse getBlogDraft(Integer blogDraftId);

    ResultResponse addBlogDraft(BlogDraftParam blogDraftParam);

    ResultResponse updateDraft(Integer blogDraftId, BlogDraftParam blogDraftParam);

    ResultResponse deleteDraft(Integer blogDraftId);

    ResultResponse getAllBlogDraft(int page, int pageSize);
}
