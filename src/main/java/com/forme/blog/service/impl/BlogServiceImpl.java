package com.forme.blog.service.impl;

import com.forme.blog.common.ResultCode;
import com.forme.blog.common.ResultResponse;
import com.forme.blog.dto.BlogParam;
import com.forme.blog.dto.ClassifyParam;
import com.forme.blog.mapper.*;
import com.forme.blog.model.entity.*;
import com.forme.blog.service.BlogService;
import com.forme.blog.util.ParamUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.forme.blog.common.BlogException.fail;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private TagToBlogMapper tagToBlogMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ClassifyMapper classifyMapper;

    @Autowired
    private ClassifyToBlogMapper classifyToBlogMapper;


    @Override
    public ResultResponse postBlog(BlogParam blogParam) {
        Blog blog=new Blog();
        BeanUtils.copyProperties(blogParam,blog);
        blog.setCreateDate(LocalDateTime.now());
        if(Objects.isNull(blog.getBlogDigest()) || blog.getBlogDigest()==""){
            String blogDigest;
            if(blog.getBlogContent().length()<= ParamUtil.DigestLength){
                blogDigest=blog.getBlogContent();
            }else{
                blogDigest=blog.getBlogContent().substring(0,ParamUtil.DigestLength);
            }
            blog.setBlogDigest(blogDigest);
        }
        blogMapper.insertSelective(blog);
        Integer blogId=blog.getBlogId();
        for(Integer tagId:blogParam.getTagIdList()){
            tagToBlogMapper.insert(new TagToBlog().setBlogId(blogId).setTagId(tagId));
        }
        return ResultResponse.success(null);
    }

    @Override
    public ResultResponse addTag(Tag tag) {
        Integer tagId=tagMapper.selectByName(tag.getTagName());
        if(!Objects.isNull(tagId)){
            return ResultResponse.failed(ResultCode.TAG_EMPTY);
        }
        if(tagMapper.insert(tag)>0) {
            return ResultResponse.success(null);
        }
        return ResultResponse.failed(ResultCode.TAG_INSERT_FAILED);
    }

    @Override
    public ResultResponse deleteTag(Integer tagId) {
        Tag tag=tagMapper.selectById(tagId);
        if(Objects.isNull(tag)){
            return ResultResponse.failed(ResultCode.TAG_NOTEMPTY);
        }
        tagToBlogMapper.deleteById(tagId);
        tagMapper.delete(tagId);
        return ResultResponse.seccess(ResultCode.TAG_DELETE_SECCESS.getMessage(), null);
    }

    @Override
    public ResultResponse addClassify(Classify classify) {
        Integer classifyId=classifyMapper.selectByClassifyName(classify.getClassifyName());
        if(!Objects.isNull(classifyId)){
            return ResultResponse.failed(ResultCode.CLASSIFY_ISEMPTY);
        }
        if(classifyMapper.insert(classify)>0){
            return ResultResponse.success(null);
        }
        return ResultResponse.failed(ResultCode.CLASSIFY_CREATE_FAILED);
    }

    @Override
    public ResultResponse updateClassify(ClassifyParam classifyParam) {
        Classify classify=new Classify();
        if(classifyMapper.selectByClassifyName(classifyParam.getClassifyName())!=null){
            return ResultResponse.failed(ResultCode.CLASSIFY_ISEMPTY);
        }
        BeanUtils.copyProperties(classifyParam,classify);
        if(classifyMapper.update(classify)>0){
            return ResultResponse.success(null);
        }
        return ResultResponse.failed(ResultCode.CLASSIFY_UPDATE_FAILED);
    }

    @Override
    public ResultResponse deleteClassify( Integer classifyId) {
        Classify classify=classifyMapper.selectByPrimaryKey(classifyId);
        if(Objects.isNull(classify)){
            return   ResultResponse.failed(ResultCode.CLASSIFY_NOT_EMPTY);
        }
        classifyMapper.delete(classifyId);
        classifyToBlogMapper.deleteById(classifyId);
        return ResultResponse.success(null);
    }

    @Override
    public ResultResponse addBlogToClassify(Integer classifyId, List<Integer> blogIdList) {
        Classify classify=classifyMapper.selectByPrimaryKey(classifyId);
        if(Objects.isNull(classify)){
            return  ResultResponse.failed(ResultCode.CLASSIFY_NOT_EMPTY);
        }
        for(Integer blogId:blogIdList){
            ClassifyToBlog classifyToBlog=classifyToBlogMapper.selectByBlogIdAndClassifyId(blogId,classifyId);
            Blog blog=blogMapper.selectByPrimaryKey(blogId);
            if(Objects.isNull(blog)){
                return ResultResponse.failed(ResultCode.BLOG_NOT_EMPTY);
            }
            if(Objects.isNull(classifyToBlog))
                classifyToBlogMapper.insert(new ClassifyToBlog().setBlogId(blogId).setClassifyId(classifyId));
        }
        return ResultResponse.success(null);
    }


}
