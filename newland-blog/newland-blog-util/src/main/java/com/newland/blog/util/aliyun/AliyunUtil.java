package com.newland.blog.util.aliyun;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.PutObjectResult;
import com.newland.blog.util.base.Result;
import com.newland.blog.util.enums.PlatformEnum;
import com.newland.blog.util.properties.AliyunProperties;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

/**
 * 阿里云工具类
 */
public final class AliyunUtil {

    /**
     * 上传图片文件
     * @param platformEnum 类型：文章，用户
     * @param file  MultipartFile文件对象
     * @param aliyun AliyunProperties 阿里云配置
     * @return
     */
    public static Result uploadFileToOss(PlatformEnum platformEnum, MultipartFile file, AliyunProperties aliyun ) {
        // 上传
        // 上传文件所在目录名，当天上传的文件放到当天日期的目录下。article/19990101/123123.png
        String folderName = platformEnum.name().toLowerCase() + "/" + DateFormatUtils.format(new Date(), "yyyyMMdd");

        // 保存到 OSS 中的文件名，采用 UUID 命名。
        String fileName = UUID.randomUUID().toString().replace("-", "");

        // 从原始文件名中，获取文件扩展名
        String fileExtensionName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        // 文件在 OSS 中存储的完整路径
        String filePath = folderName + "/" + fileName + fileExtensionName;

        OSS ossClient = null;
        try {
            // 获取 OSS 客户端实例
            ossClient = new OSSClientBuilder().build(aliyun.getEndpoint(), aliyun.getAccessKeyId(), aliyun.getAccessKeySecret());

            // 上传文件到OSS 并响应结果
            PutObjectResult putObjectResult = ossClient.putObject(aliyun.getBucketName(), filePath, file.getInputStream());

            ResponseMessage response = putObjectResult.getResponse();
            if(response == null) {
                // 上传成功

                // 返回上传文件的访问完整路径
                return Result.ok( aliyun.getBucketDomain() + filePath );
            }else {
                // 上传失败，OOS服务端会响应状态码和错误信息
                String errorMsg = "响应的错误状态码是【" + response.getStatusCode() +"】，" +
                        "错误信息【"+response.getErrorResponseAsString()+"】";
                return Result.error(errorMsg);
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        } finally {
            if (ossClient != null) {
                // 关闭OSSClient。
                ossClient.shutdown();
            }
        }
    }

    /**
     * 根据文件url删除
     * @param fileUrl
     */
    public static Result delete(String fileUrl, AliyunProperties aliyun) {
        // 去除文件 url 中的 Bucket域名 article/20200729/9d83082760e84c15a685d6e61338174a.png
        String filePath = fileUrl.replace(aliyun.getBucketDomain(), "");

        OSS ossClient = null;
        try {
            ossClient = new OSSClientBuilder().build(aliyun.getEndpoint(), aliyun.getAccessKeyId(), aliyun.getAccessKeySecret());
            // 删除
            ossClient.deleteObject(aliyun.getBucketName(), filePath);
            return Result.ok();
        } catch (Exception e) {
            return Result.error("删除失败："+e.getMessage());
        }finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}