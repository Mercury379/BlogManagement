package com.newland.blog;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = {"com.newland.blog.question.service"}) // 扫描 @Feign 接口进行远程调用
@EnableDiscoveryClient // 开启Nacos服务注册与发现功能
@EnableSwagger2Doc // 开启swagger功能
@SpringBootApplication
public class QuestionApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuestionApplication.class, args);
    }
}
