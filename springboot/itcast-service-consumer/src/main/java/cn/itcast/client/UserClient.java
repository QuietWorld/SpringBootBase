package cn.itcast.client;

import cn.itcast.domain.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 使用@FeignClient声明该接口为Feign的远程调用客户端接口
 * value属性指定远程调用的服务id(指定当前feign客户端的远程调用对象)
 * fallback属性指定当前feign客户端的熔断处理类
 * feign客户端使用动态代理生成接口实现类，在接口实现类中使用RestTemplate进行远程调用
 * 接口实现类在Spring容器中的id是@FeignClient的value属性值
 */
@FeignClient(value = "service-provider", fallback = UserClientFallback.class)
public interface UserClient {

    // 声明需要调用的微服务方法
    @GetMapping("account/{id}")
    @ResponseBody
    Account findById(@PathVariable("id") Integer id);

    @GetMapping("account/findAll")
    Object findAll();
}
