package com.newland.blog.article.api;

import com.newland.blog.article.service.IAdvertService;
import com.newland.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "广告管理API接口", description = "广告管理API接口，不需要通过身份认证就可以访问下面的接口")
@RestController
@RequestMapping("/api/advert")
public class ApiAdvertController {

    @Autowired
    private IAdvertService advertService;

    @ApiImplicitParam(name = "position", value = "广告位置编号", required = true)
    @ApiOperation("查询指定广告查询的所有广告信息（状态为正常的）")
    @GetMapping("/show/{position}")
    public Result show(@PathVariable("position") int position) {
        return advertService.findByPosition(position);
    }

}
