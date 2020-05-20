package com.itheima;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * springboot启动类
 * @SpringBootApplication:
 * 配置该注解相当于配置了@Configuration @EnableAutoConfiguration @ComponentScan
 * @author zc
 */
@SpringBootApplication
@MapperScan("com.itheima.dao")   // 使用通用Mapper后该注解要换成通用Mapper的@MapperScan
public class SpringBootApplicationStart {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationStart.class);
    }
}
