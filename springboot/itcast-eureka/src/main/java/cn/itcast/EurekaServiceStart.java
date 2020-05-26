package cn.itcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //启用eureka服务器
public class EurekaServiceStart {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServiceStart.class);
    }
}
