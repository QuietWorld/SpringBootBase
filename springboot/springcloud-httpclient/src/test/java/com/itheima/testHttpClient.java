package com.itheima;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.domain.Account;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.IOException;


/**
 * HttpClient就相当于浏览器，通信协议是基于TCP的Http协议
 * 应用：网络爬虫
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootStart.class)
public class testHttpClient {

    private CloseableHttpClient httpClient;
    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void init(){
        // 初始化HttpClient，相当于打开浏览器
        this.httpClient = HttpClients.createDefault();
    }

    @Test
    public void testGet() throws IOException {
        // 相当于输入url
        HttpGet httpGet = new HttpGet("https://www.baidu.com");
        // 相当于敲击回车访问
        String response = httpClient.execute(httpGet, new BasicResponseHandler());
        // 相当于页面输出响应
        System.out.println(response);
    }

    /**
     * 测试使用HttpClient远端连接我的服务
     */
    @Test
    public void testMyService() throws IOException {
        HttpGet httpGet = new HttpGet("http://localhost/account/1");
        String response = httpClient.execute(httpGet, new BasicResponseHandler());
        System.out.println(response);
        // json对象->java对象
        System.out.println(objectMapper.readValue(response, Account.class));
    }
}
