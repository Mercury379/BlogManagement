package com.newland.blog.article.controller;


import com.newland.blog.article.req.AdvertREQ;
import com.newland.blog.article.service.IAdvertService;
import com.newland.blog.entities.Advert;
import com.newland.blog.util.aliyun.AliyunUtil;
import com.newland.blog.util.base.Result;
import com.newland.blog.util.enums.PlatformEnum;
import com.newland.blog.util.properties.AliyunProperties;
import com.newland.blog.util.properties.BlogProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * <p>
 * 广告信息表 前端控制器
 * </p>
 */
@Api(value = "广告管理接口", description = "广告管理接口,提供广告的增、删、改、查")
@RestController
@RequestMapping("/advert")
@Validated
public class AdvertController {

    @Autowired
    private IAdvertService advertService;

    @Autowired
    private BlogProperties blogProperties;

    @ApiOperation("根据广告标题与状态查询广告分页列表接口")
    @PostMapping("/search") // /article/advert/search
    public Result search(@RequestBody AdvertREQ req) {
        return advertService.queryPage(req);
    }

    @ApiImplicitParam(name = "id", value = "广告ID", required = true)
    @ApiOperation("删除广告接口")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") String id) {
        return advertService.deleteById(id);
    }

    @ApiImplicitParam(name = "id", value = "广告ID", required = true)
    @ApiOperation("查询广告详情接口")
    @GetMapping("/{id}")
    public Result view(@PathVariable("id") String id) {
        return Result.ok( advertService.getById(id) );
    }

    @ApiOperation("修改广告信息接口")
    @PutMapping
    public Result update(@RequestBody Advert advert) {
        advert.setUpdateDate(new Date());
        advertService.updateById(advert);
        return Result.ok();
    }

    @ApiOperation("新增广告信息接口")
    @PostMapping

    public Result save(@Validated Advert advert, @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            // 将文件保存到OSS服务器
            AliyunProperties aliyun = blogProperties.getAliyun();
            Result fileRes = AliyunUtil.uploadFileToOss(PlatformEnum.ARTICLE, file, aliyun);
            if(fileRes.getCode() != 20000) {
                return Result.error(fileRes.getMessage());
            }
            advert.setImageUrl(fileRes.getData().toString());
        }
        advertService.save(advert);

        return Result.ok();
    }


}
