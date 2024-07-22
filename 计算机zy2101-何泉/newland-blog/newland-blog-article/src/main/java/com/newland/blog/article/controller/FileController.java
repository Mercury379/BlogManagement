package com.newland.blog.article.controller;

import com.newland.blog.util.aliyun.AliyunUtil;
import com.newland.blog.util.base.Result;
import com.newland.blog.util.enums.PlatformEnum;
import com.newland.blog.util.properties.AliyunProperties;
import com.newland.blog.util.properties.BlogProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@Api(value ="文件管理接口",description ="文件管理接口，实现文件上传，下载")
@RequestMapping("/file")
@RestController
public class FileController {

    @Autowired
    private  BlogProperties blogProperties;

    @ApiOperation("上传文件至OSS服务器")
    @PostMapping("/upload")
    public Result upload(@RequestParam MultipartFile file){

        //获取阿里云OSS相关配置信息
        AliyunProperties aliyun=blogProperties.getAliyun();

        //实现本地文件上传阿里云OSS
       return AliyunUtil.uploadFileToOss(PlatformEnum.ARTICEL,file,aliyun);

    }





}
