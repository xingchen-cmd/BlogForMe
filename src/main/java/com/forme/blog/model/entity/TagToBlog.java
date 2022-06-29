package com.forme.blog.model.entity;


import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;


/**
 * <p>
 * 
 * </p>
 *
 * @author liQuan
 * @since 2022-06-09
 */
@Data
@Accessors(chain = true)
public class TagToBlog  {

    private Integer blogId;

    private Integer tagId;


}
