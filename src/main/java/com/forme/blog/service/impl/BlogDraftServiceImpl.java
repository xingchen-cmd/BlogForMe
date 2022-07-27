package com.forme.blog.service.impl;

import com.forme.blog.common.ResultCode;
import com.forme.blog.common.ResultResponse;
import com.forme.blog.dto.BlogDraftParam;
import com.forme.blog.mapper.BlogDraftMapper;
import com.forme.blog.model.entity.BlogDraft;
import com.forme.blog.service.BlogDraftService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class BlogDraftServiceImpl implements BlogDraftService {

    @Autowired
    private BlogDraftMapper blogDraftMapper;

    @Override
    public ResultResponse getBlogDraft(Integer blogDraftId) {
        BlogDraft blogDraft=null;
        blogDraft=blogDraftMapper.selectByPrimaryKey(blogDraftId);
        if(Objects.isNull(blogDraft)){
            return ResultResponse.failed(ResultCode.BLOGDRAFT_NOT_EMPTY);
        }
        return ResultResponse.success(blogDraft);
    }

    @Override
    public ResultResponse addBlogDraft(BlogDraftParam blogDraftParam) {
        BlogDraft blogDraft=new BlogDraft();
        BeanUtils.copyProperties(blogDraftParam,blogDraft);
        if(blogDraft.getCreateDate()==null){
            blogDraft.setCreateDate(LocalDateTime.now());
        }
        if(blogDraftMapper.insertSelective(blogDraft)>0){
            return ResultResponse.success(null);
        }
        return ResultResponse.failed(ResultCode.BLOGDRAFT_ADD_FAILED);
    }

    @Override
    public ResultResponse updateDraft(Integer blogDraftId, BlogDraftParam blogDraftParam) {
        BlogDraft blogDraft=blogDraftMapper.selectByPrimaryKey(blogDraftId);
        if(Objects.isNull(blogDraft)){
            return ResultResponse.failed(ResultCode.BLOGDRAFT_NOT_EMPTY);
        }
        BeanUtils.copyProperties(blogDraftParam,blogDraft);
        if(blogDraftMapper.updateSelective(blogDraft)>0){
            return ResultResponse.success(null);
        }
        return ResultResponse.failed(ResultCode.BLOGDRAFT_UPDATE_FAILED);
    }

    @Override
    public ResultResponse deleteDraft(Integer blogDraftId) {
        BlogDraft blogDraft=blogDraftMapper.selectByPrimaryKey(blogDraftId);
        if(Objects.isNull(blogDraft)){
            return ResultResponse.failed(ResultCode.BLOG_NOT_EMPTY);
        }
        blogDraftMapper.delete(blogDraftId);
        return ResultResponse.success(null);
    }

    @Override
    public ResultResponse getAllBlogDraft(int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        List<BlogDraft> blogDraftList=blogDraftMapper.selectAll();
        return ResultResponse.success(new PageInfo<>(blogDraftList));
    }


}
