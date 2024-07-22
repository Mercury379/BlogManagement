package com.newland.blog.article.controller;


import com.newland.blog.article.req.AdvertREQ;
import com.newland.blog.article.req.ArticleREQ;
import com.newland.blog.article.service.IAdvertService;
import com.newland.blog.entities.Advert;
import com.newland.blog.entities.Article;
import com.newland.blog.util.base.Result;
import com.newland.blog.util.enums.AdvertStatusEnum;
import com.newland.blog.util.enums.ArticleStatusEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

/**
 * <p>
 * 广告信息表 前端控制器
 * </p>
 */
@Api(value = "广告管理接口", description = "广告管理接口, 提供广告的增删改查")
@RestController
@RequestMapping("/advert")
public class AdvertController {
    @Autowired
    private IAdvertService advertService;

    //1  根据广告标题与状态分页查询广告
    @ApiOperation("1  根据广告标题与状态分页查询广告")
    @PostMapping("/search")
    public Result search(@RequestBody AdvertREQ req){
        return advertService.queryPage(req);
    }

    //2 根据ID实现广告删除
    @ApiImplicitParam(name = "id", value = "广告ID", required = true)
    @ApiOperation("2 根据ID实现广告删除")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") String id) {
        //假删除，只是将广告的状态改为禁用
        return advertService.updateStatus(id, AdvertStatusEnum.DISNABLE);
        //真删除，真正从数据库中删除该记录
        //return advertService.delete(id);
    }

    //3 根据ID查询广告详情接口
    @ApiOperation("3 根据ID查询广告详情接口")
    @ApiImplicitParam(name = "id", value = "广告ID", required = true)
    @GetMapping("/{id}")
    public Result view(@PathVariable String id) {
        return advertService.findAdvertById(id);
    }


    //4 修改广告信息接口
    @ApiOperation("4 修改广告信息接口")
    @PutMapping
    public Result update(@RequestBody Advert advert) {
        return advertService.updateOrSave(advert);
    }

    //5  新增广告
    @ApiOperation("5  新增广告")
    @PostMapping
    public Result save(@RequestBody Advert advert) {
        return advertService.updateOrSave(advert);
    }
}
