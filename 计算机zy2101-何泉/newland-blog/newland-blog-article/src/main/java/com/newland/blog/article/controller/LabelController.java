package com.newland.blog.article.controller;


import com.newland.blog.article.req.LabelREQ;
import com.newland.blog.article.service.ILabelService;
import com.newland.blog.entities.Label;
import com.newland.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 标签表 前端控制器
 */
@Api(value = "标签管理接口", description = "标签管理接口，提供标签的增、删、改、查")
@RestController
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private ILabelService labelService;

    @ApiOperation("根据标签名称与分类id查询页面分页列表接口")
    @PostMapping("/search") // post方式请求 /label/search
    public Result search(@RequestBody LabelREQ req) {
        return labelService.queryPage(req);
    }

    @ApiImplicitParam(name = "id", value = "标签ID", required = true)
    @ApiOperation("查询标签详情接口")
    @GetMapping("/{id}")
    public Result view(@PathVariable String id) {
        Label label = labelService.getById(id);
        return Result.ok(label);
    }

    @ApiOperation("修改标签信息接口")
    @PutMapping // put 请求 /label
    public Result update(@RequestBody Label label) {
        labelService.updateById(label);
        return Result.ok();
    }

    @ApiOperation("新增标签信息接口")
    @PostMapping // post 请求 /label
    public Result save(@RequestBody Label label) {
        labelService.save(label);
        return Result.ok();
    }

    @ApiImplicitParam(name = "id", value = "标签ID", required = true)
    @ApiOperation("删除标签接口")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") String id) {
        labelService.removeById(id);
        return Result.ok();
    }


}
