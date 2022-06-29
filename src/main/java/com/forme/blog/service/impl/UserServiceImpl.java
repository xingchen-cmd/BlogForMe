package com.forme.blog.service.impl;

import com.forme.blog.common.BlogException;
import com.forme.blog.common.IErrorCode;
import com.forme.blog.common.ResultCode;
import com.forme.blog.common.ResultResponse;
import com.forme.blog.config.UserDetailsImpl;
import com.forme.blog.dto.UpdateAdminPasswordParam;
import com.forme.blog.dto.UserInfo;
import com.forme.blog.dto.UserLogin;
import com.forme.blog.dto.UserParam;
import com.forme.blog.mapper.UserMapper;
import com.forme.blog.model.entity.User;
import com.forme.blog.service.UserService;
import com.forme.blog.util.JwtTokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;

import static com.forme.blog.common.BlogException.fail;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER= LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtTokenUtils jwt;


    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User getUserByUserName(String userName) {
        User user=userMapper.selectByUserName(userName);
        if(user!=null) return  user;
        return null;
    }

    @Override
    public ResultResponse register(UserParam userParam) {
        Integer count=userMapper.selectUserCount();
        if(count.equals(1)){
            fail("已经注册过了");
        }
        User user=new User();
        BeanUtils.copyProperties(userParam,user);
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        user.setCreateDate(LocalDateTime.now());
       if(userMapper.insertSelective(user)>0){
           return ResultResponse.seccess("注册成功",null);
       }

        return ResultResponse.failed(ResultCode.LOGIN_FAILED);
    }

    @Override
    public ResultResponse login(UserLogin userLogin) {

//        User user=userMapper.selectByUserName(userLogin.getUserName());
//        if(Objects.isNull(user) || passwordEncoder.matches(userLogin.getPassword(),user.getUserPassword())){
//            return ResultResponse.failed(ResultCode.LOGINERROR);
//        }
//        HashMap<String,Object> result=new HashMap<>();
//        result.put("token",jwt.generationToken(String.valueOf(user.getUserId())));
//        return ResultResponse.success(result);
        //
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userLogin.getUserName(),userLogin.getPassword());
        Authentication authentication=authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authentication)){
            throw new RuntimeException("账号或密码错误");
        }
        UserDetailsImpl userDetails= (UserDetailsImpl) authentication.getPrincipal();
        String token= jwt.generationToken(userDetails.getUser().getUserId().toString());
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("token",token);
        return ResultResponse.success(hashMap);
    }

    @Override
    public String refreshToken(String oldToken) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return null;
    }

    @Override
    public int updatePassword(UpdateAdminPasswordParam updateAdminPasswordParam) {
        return 0;
    }

    @Override
    public int update(UserInfo userInfo) {
        return 0;
    }

    @Override
    public UserInfo getUserInfo(Integer id) {
        return null;
    }
}
