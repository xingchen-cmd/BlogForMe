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

public class Classify  {


    private Integer classifyId;
    @NotEmpty
    @ApiModelProperty(value = "分类名",required = true)
    private String classifyName;


}
