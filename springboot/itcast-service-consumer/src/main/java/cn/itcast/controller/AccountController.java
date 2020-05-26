package cn.itcast.controller;


import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * Hystrix防止雪崩效应（findById方法中有雪崩效应解释）的两个机制：
 * 1.线程隔离： Hystrix为每个微服务都提供了一个线程池，每当一个微服务被请求时都将开启一个独立的线程处理本次请求。
 *   线程隔离：
 *   请求超时）Hystrix为服务提供方提供一个小的线程池，服务调用方不再直接调用服务提供方，
 * 而是首先会为本次调用开启一个新的线程，这样当某次请求中，服务提供方出现异常，
 * 那么影响的仅仅是当前线程。服务调用方调用服务提供方后一段时间没有收到响应，
 * 那么就认定本次调用请求超时，从而释放线程资源和线程的栈内存）。
 *   线程池满）但是如果有多个请求，而每个请求去调用服务提供方时，服务提供方由于异常都不能
 * 响应，由于每个请求都有一个线程，而线程数量是有限的，所有会造成线程池满。
 *   请求超时和线程池满会触发Hystrix的服务降级。
 * 服务降级）：调用服务失败后，通过@HystrixCommand(fallbackMethod = "局部熔断方法名")指定另外一个局部熔断方法执行。
 * 2.服务熔断：
 *  Hystrix会对请求情况计数（方法调用服务的情况），如果一定时间内请求失败百分比达到阈值（方法调用服务失败），
 *  则触发熔断器，熔断器状态变为Open。默认失败比例阈值是50%，请求次数不少于20次。
 *  三个状态：
 *  a）Closed：关闭服务熔断,所有请求都能正常访问，方法正常调用服务提供方
 *  b）Open：打开服务熔断，方法不再调用服务提供方法，而是该方法的所有请求都将直接降级，该状态默认持续5秒。
 *  c) HalfOpen: 半开服务熔断，Open状态不是持久的，默认5秒后会进入HalfOpen状态，此时允许部分
 *  请求访问，方法尝试调用服务提供方，如果正常调用进入Closed状态，如果依然无法调用再次进入Open状态，依次循环。
 *
 */
@Controller
@RequestMapping("/consumer/account")
@DefaultProperties(defaultFallback = "globalFallbackMethod")
public class AccountController {

    @Autowired
    private RestTemplate restTemplate;

    // eureka客户端，可以获取从eureka服务器端拉取的服务列表
    // @Autowired
    // private DiscoveryClient discoveryClient;


    @GetMapping("/findById")
    @ResponseBody
    @HystrixCommand//(fallbackMethod = "findByIdFallback")
    public String findById(@RequestParam("id") Integer id) {
        // 使用客户端拉取指定id的服务实例，因为服务实例有可能是集群所以返回list集合
        // List<ServiceInstance> instances = discoveryClient.getInstances("service-provider");
        // 获取服务实例
        // ServiceInstance serviceInstance = instances.get(0);

        // 这里去调用服务，如果被调用的服务出现异常，那么这个方法就会一直占用一个线程资源和一些内存空间。
        // 如果有多个请求来请求该方法，那么就会导致该方法的线程池满，而且会造成web容器的内存溢出（该方法由于一直在尝试调用服务而无法出栈以释放内存），
        // 从而导致这一个tomcat服务器中部署的其他服务（方法）也没法正常执行（内存满了，其他方法不能进栈执行了），这就是雪崩效应。
        return this.restTemplate.getForObject("http://service-provider/account/" + id, String.class);
    }

    /**
     * 局部回退方法
     * 当findById调用服务失败时，就会执行该方法作服务降级处理！
     * @param id 和被熔断方法有相同参数列表
     * @return   和被熔断方法有相同返回值
     * findById方法调用服务超时或者服务提供方的线程池已满，会触发Hystrix的服务降级，通过@HystrixCommand(fallbackMethod = "findByIdFallback")
     * 可以指定局部熔断处理方法。服务降级不会导致线程资源被占用，方法无法出栈，内存溢出从而导致整个web容器崩溃
     * 的问题，只是某个请求一段时间内不再可访问。
     */
    public String findByIdFallback(Integer id){
        return "服务器正忙，请稍后再试！";
    }

    /**
     * 全局回退方法，该方法返回值和整个类中所有方法返回值保持一致。
     * 方法的参数列表必须为空，在类上面使用@DefaultPerperties（defaultFallbackMethod="全局方法名"）
     * 来声明该方法为全局回退方法。
     * @return
     */
    public String globalFallbackMethod(){
        return "当前服务正忙，请稍后再试！";
    }
}
