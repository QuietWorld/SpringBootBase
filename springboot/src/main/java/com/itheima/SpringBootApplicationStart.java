package com.itheima;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




/**
 * springboot启动类
 * @SpringBootApplication:
 * 配置该注解相当于配置了@Configuration @EnableAutoConfiguration @ComponentScan
 * @author zc
 */
@SpringBootApplication
public class SpringBootApplicationStart {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationStart.class);
    }
}
