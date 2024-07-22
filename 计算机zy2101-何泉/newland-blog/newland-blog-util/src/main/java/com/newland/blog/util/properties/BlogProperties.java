package com.newland.blog.util.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
//1  配置类
@Setter
@Getter
@Component  //将配置类 添加到当前的容器中
@ConfigurationProperties(prefix ="newland.blog")
public class BlogProperties  implements Serializable{

    private AliyunProperties aliyun;

}
