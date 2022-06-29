package com.forme.blog.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ClassifyParam {
    @NotNull
    @ApiModelProperty(value = "分类名id",required = true)
    private Integer classifyId;
    @NotEmpty
    @ApiModelProperty(value = "分类名更改后的名字",required = true)
    private String classifyName;
}
