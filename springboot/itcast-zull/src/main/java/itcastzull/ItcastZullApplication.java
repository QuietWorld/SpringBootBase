package itcastzull;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @EnableZuulProxy : 启用zuul服务网关
 * @author zc
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ItcastZullApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItcastZullApplication.class, args);
    }

}
