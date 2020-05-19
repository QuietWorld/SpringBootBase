package com.itheima.config;

import com.itheima.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * 在springboot应用中配置拦截器：
 * 1.自定义一个拦截器 MyInterceptor
 * 2.在config包下使用@Configuration声明一个类为java的配置类 WebMvcConfiguration
 * 3.配置类实现WebMvcConfigurer接口
 * 4.重写addInterceptors(InterceptorRegistry registry)方法
 *注意：spring的拦截器只拦截方法
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private HandlerInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         *       /** 和 /*的区别：
         *       /*只能拦截任意的一级目录
         *       /**拦截任意目录
         */
        registry.addInterceptor(interceptor).addPathPatterns("/**");
    }
}
