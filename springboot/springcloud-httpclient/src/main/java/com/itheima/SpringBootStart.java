package com.itheima;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.itheima.dao")
public class SpringBootStart {

    private static final Logger log = LoggerFactory.getLogger(SpringBootStart.class);

    public static void main(String[] args) {
        log.info("spring main is running");
        SpringApplication.run(SpringBootStart.class);
    }

    @Bean
    public ObjectMapper createObjectMapper(){
        return new ObjectMapper();
    }
}
