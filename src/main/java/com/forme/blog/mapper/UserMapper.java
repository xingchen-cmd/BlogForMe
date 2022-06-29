package com.forme.blog.mapper;


import com.forme.blog.model.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liQuan
 * @since 2022-06-09
 */
public interface UserMapper{
    Integer selectUserCount();

    User selectByUserName(String userName);

    User selectByUserNameAndPassword(@Param("userName") String userName,@Param("password") String password);

    User selectByPrimaryKey(Integer id);

    int insertSelective(User user);

    int updateSelective(User user);

    int delete(Integer id);

}
