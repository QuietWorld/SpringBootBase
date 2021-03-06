package com.itheima.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/sayHello")
    @ResponseBody // 将方法的返回值输出到页面
    public String sayHello(){
        log.info("sayHello is running");
        return "sayHello is running";
    }

}
