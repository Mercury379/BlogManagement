package com.newland.blog.util.properties;

import lombok.Data;

import java.io.Serializable;

@Data
public class AliyunProperties implements Serializable {

    /**
     * oss的端点信息
     */
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;

    /**
     * 存储空间名称
     */
    private String bucketName;
    /**
     * Buckect名称, 访问文件时作为基础URL
     */
    private String bucketDomain;

}
