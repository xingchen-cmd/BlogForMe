package com.forme.blog.model.entity;


import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;


/**
 * <p>
 * 
 * </p>
 *
 * @author liQuan
 * @since 2022-06-09
 */
@Data

public class Tag {

    private Integer tagId;

    @NotEmpty
    @ApiModelProperty(value = "标签名",required = true)
    private String tagName;


}
