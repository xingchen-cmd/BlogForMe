package com.forme.blog.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class BlogListParam {
    @NotNull
    @ApiModelProperty(value = "博客id集合",required = true)
    private List<Integer> blogIdList;
}
