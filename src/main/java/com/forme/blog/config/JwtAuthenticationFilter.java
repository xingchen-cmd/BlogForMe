package com.forme.blog.config;

import com.alibaba.druid.util.StringUtils;
import com.forme.blog.common.BlogException;
import com.forme.blog.mapper.UserMapper;
import com.forme.blog.model.entity.User;
import com.forme.blog.util.JwtTokenUtils;
import com.forme.blog.util.RedisUtil;
import com.github.pagehelper.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static com.forme.blog.common.BlogException.fail;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    @Autowired
    private JwtTokenUtils jwt;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token=request.getHeader("token");
        if(StringUtil.isEmpty(token)){
            filterChain.doFilter(request,response);
            return;
        }
        String userId=null;
        try {
            userId = jwt.getUserIdFromToken(token);
        }catch (Exception e){
            LOGGER.info("jwt验证失败");
            request.setAttribute("filter.error", e);
            request.getRequestDispatcher("/401").forward(request, response);
        }


        //jwt身份认证
        User user = userMapper.selectByPrimaryKey(Integer.parseInt(userId));
        UserDetails userDetails=this.userDetailsService.loadUserByUsername(user.getUserName());
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);
    }
}
