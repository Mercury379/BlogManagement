package com.newland.blog.util.properties;

import lombok.Data;

import java.io.Serializable;

@Data
public class AliyunProperties {

    private  String endpoint;
    private  String accessKeyId;
    private  String accessKeySecret;
    /**
     * 存储空间名称
     */
    private  String bucketName;

    /**
     * bucket的名称，访问文件的时候作为基础路径
     */
    private  String bucketDomain;


}
