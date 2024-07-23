package com.newland.blog.util.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
public class AliyunProperties implements Serializable {

    /**
     * oss的端点信息
     */
    @Value("${ali.oss.endpoint}")
    private String endpoint;

    @Value("${ali.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${ali.oss.accessKeySecret}")
    private String accessKeySecret;

    /**
     * 存储空间名称
     */
    @Value("${ali.oss.bucketName}")
    private String bucketName;
    /**
     * Buckect名称, 访问文件时作为基础URL
     */
    @Value("${ali.oss.bucketDomain}")
    private String bucketDomain;

}
