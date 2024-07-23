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

/**
 * 文件控制器
 */
@Api(value = "文件管理接口", description = "文件管理接口，上传或删除图片文件")
@RequestMapping("/file")
@RestController
public class FileController {

    @Autowired
    private BlogProperties blogProperties;

    @ApiOperation("上传文件到OSS服务器")
    @PostMapping("/upload") // /article/file/upload
    public Result upload(@RequestParam("file") MultipartFile file ) {
        // 获取阿里云 OSS 相关配置信息
        AliyunProperties aliyun = blogProperties.getAliyun();

        return AliyunUtil.uploadFileToOss(PlatformEnum.ARTICEL, file, aliyun);
    }

    @ApiImplicitParam(name = "fileUrl", value = "要删除的文件URL", required = true)
    @ApiOperation("根据文件URL删除在OSS服务器中对应文件")
    @DeleteMapping("/delete") // /article/file/delete
    public Result delete(@RequestParam(value = "fileUrl", required = true)
                                     String fileUrl) {
        return AliyunUtil.delete(fileUrl, blogProperties.getAliyun());
    }


}
