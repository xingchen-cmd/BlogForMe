package com.forme.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.forme.blog.mapper")
@EnableOpenApi
public class BlogApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext run=SpringApplication.run(BlogApplication.class, args);
        System.out.println("111");
    }

}
