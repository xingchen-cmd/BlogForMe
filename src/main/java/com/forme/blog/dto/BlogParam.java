package com.forme.blog.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class BlogParam {

    @NotEmpty
    @ApiModelProperty(value = "博客标题",required = true)
    private String blogTitle;
    @NotEmpty
    @Size(min = 30)
    @ApiModelProperty(value = "博客内容",required = true)
    private String blogContent;
    @NotNull
    @ApiModelProperty(value = "作者id",required = true)
    private Integer blogAuthorId;
    @ApiModelProperty(value = "博客摘要")
    private String blogDigest;
    @NotNull
    @ApiModelProperty(value = "标签",required = true)
    private List<Integer> tagIdList;
}
