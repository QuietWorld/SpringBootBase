package cn.itcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//@EnableEurekaClient
@EnableDiscoveryClient
@MapperScan("cn.itcast.dao")
public class ProviderRun {

    public static void main(String[] args) {
        SpringApplication.run(ProviderRun.class);
    }
}
