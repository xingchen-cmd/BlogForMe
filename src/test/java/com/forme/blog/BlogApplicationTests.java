package com.forme.blog;

import com.forme.blog.mapper.ClassifyToBlogMapper;
import com.forme.blog.model.entity.ClassifyToBlog;
import com.forme.blog.util.JwtTokenUtils;
import com.forme.blog.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class BlogApplicationTests {
    @Autowired
    private JwtTokenUtils jwt;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ClassifyToBlogMapper classifyToBlogMapper;
    @Test
    void contextLoads() {
        String userId="17408109";
        String token = jwt.generationToken(userId);
        System.out.println(token);
        System.out.println("token是否合法："+jwt.validateToken(token, userId));
        String newToken= jwt.refreshToken(token);
        System.out.println("oldToken是否合法："+jwt.validateToken(token, "17408100"));
        System.out.println("newToken是否合法："+jwt.validateToken(newToken, "17408109"));
    }

    @Test
    public void PasswordEncoder(){
        String encode = passwordEncoder.encode("123456");
        String encode1 = passwordEncoder.encode("123456");
        System.out.println(encode);
        System.out.println(encode1);
        System.out.println(passwordEncoder.matches("123456", "$2a$10$MR34qkQxzYQNAd7yKBgl9.bpOtYiyFWqdCELK8ZDda0aUfkjUs2A2"));
        System.out.println(passwordEncoder.matches("123456", "$2a$10$8SRiqaKoGP/QtqVT5RJUze3Mw0Jq3kvp8t2IwX0BFhq2gxY54U4ku"));

    }

    @Test
    public void A()throws InterruptedException{
        redisTemplate.opsForValue().set("key1","半岛铁盒");
        System.out.println(redisTemplate.opsForValue().get("key1"));
        redisTemplate.opsForValue().set("key2","这条消息会过期哦",30, TimeUnit.SECONDS);
        System.out.println(redisTemplate.opsForValue().get("key2"));
        Thread.sleep(2000);
        System.out.println(redisTemplate.hasKey("key2"));
        System.out.println(redisTemplate.opsForValue().get("key2"));

    }

    @Test
    public void redisTest()throws InterruptedException{
        redisUtil.set("userId",17408109);
        redisUtil.incr("userId",20);
    }

    @Test
    public void rrtest(){
        ClassifyToBlog classifyToBlog=classifyToBlogMapper.selectByBlogIdAndClassifyId(1,1);
        System.out.println(classifyToBlog != null);

    }
}
