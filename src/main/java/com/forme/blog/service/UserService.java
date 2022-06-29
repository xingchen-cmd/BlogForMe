package com.forme.blog.service;

import com.forme.blog.common.ResultResponse;
import com.forme.blog.dto.UpdateAdminPasswordParam;
import com.forme.blog.dto.UserInfo;
import com.forme.blog.dto.UserLogin;
import com.forme.blog.dto.UserParam;
import com.forme.blog.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 后台博客管理
 */
public interface UserService {
    /**
     * 根据用户名获取后台用户信息
     */
    User getUserByUserName(String userName);

    /**
     * 注册账号
     * @return
     */

    ResultResponse register(UserParam userParam);


    /**
     * 登录
     * @param userLogin
     * @return token 返回token值
     */
    ResultResponse login(UserLogin userLogin);

    /**
     * 刷新token
     * @param oldToken
     * @return
     */
    String refreshToken(String oldToken);

    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 修改密码
     */
    int updatePassword(UpdateAdminPasswordParam updateAdminPasswordParam);
    /**
     * 修改用户信息
     */
    int update(UserInfo userInfo);
    /**
     * 获取用户信息
     */
    UserInfo getUserInfo(Integer id);


}
