package com.itheima.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/sayHello")
    @ResponseBody // 将方法的返回值输出到页面
    public String sayHello(){
        System.out.println("sayHello执行了");
        return "sayHello is running";
    }

}