package com.forme.blog.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class TagParam {

    @NotEmpty
    @ApiModelProperty(value = "标签名",required = true)
    private Integer tagId;
}
