package cn.itcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


/**
 * @EnableEurekaClient和@EnableDiscoveryClient注解的作用一样，都是启用eureka客户端，
 * 但是@EnableDiscoveryClient是属于spring-cloud-core包中的注解，而@EnableEurekaClient是属于netflix的注解
 * 所以前者显然在Spring应用中兼容性更好，所以更推荐使用
 */
//@SpringBootApplication
//@EnableEurekaClient
//@EnableDiscoveryClient  // 启用eureka客户端
//@EnableCircuitBreaker   // 启用熔断器
@SpringCloudApplication
@EnableFeignClients
public class ConsumerRun {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerRun.class);
    }

    @Bean
    @LoadBalanced  // 开启负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
