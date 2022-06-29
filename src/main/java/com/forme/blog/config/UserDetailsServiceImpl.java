package com.forme.blog.config;

import com.forme.blog.mapper.UserMapper;
import com.forme.blog.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userMapper.selectByUserName(username);
        if(Objects.isNull(user)){
            throw new RuntimeException("用户或密码错误");
        }

        return new UserDetailsImpl(user);
    }
}
