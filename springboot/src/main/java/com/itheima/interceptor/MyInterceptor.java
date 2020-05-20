package com.itheima.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 */
@Component("myInterceptor")
public class MyInterceptor implements HandlerInterceptor {

    // 使用slf4j进行日志记录
    private static final Logger log = LoggerFactory.getLogger(MyInterceptor.class);

    /**
     * 在handler方法执行之前执行
     * @param handler 目标方法
     * @return false：拦截    true：放行
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle is running");
        return true
                ;
    }

    // 在目标资源执行之后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle is running");
    }

    // 成功响应后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion is running");
    }
}
