package cn.itcast.controller;


import cn.itcast.client.UserClient;
import cn.itcast.domain.Account;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;


/**
 * 使用Feign组件进行Http接口的远程调用：
 * Feign是一个远程调用组件，帮助我们以更优雅和便捷的方法来进行服务的远程调用。
 * Feign整合了Ribbon和Eureka和Hystrix，SpringCloud对Feign进行了增强，使Feign支持了SpringMVC的注解
 * 使用：
 * 1.引入Feign组件依赖
 * 2.在引导类上面使用@EnableFeignClients启用Feign组件
 * 3.定义一个接口并添加一些注解即可完成远程调用
 */
@Controller
@RequestMapping("/consumer/account/feign")
public class AccountController_Feign {

    @Autowired
    private UserClient userClient;

    @GetMapping("/findById")
    @ResponseBody
    public String findById(@RequestParam("id") Integer id) {
        return userClient.findById(id).toString();
    }

    @GetMapping("/findAll")
    @ResponseBody
    public String findAll(){
        // 使用feign客户端进行远程服务调用
        return userClient.findAll().toString();
    }

}
