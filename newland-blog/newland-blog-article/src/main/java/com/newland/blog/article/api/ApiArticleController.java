package com.newland.blog.article.api;

import com.newland.blog.article.req.ArticleListREQ;
import com.newland.blog.article.service.IArticleService;
import com.newland.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "文章管理API接口", description = "文章管理API接口，不需要通过身份认证就可以访问下面的接口")
@RestController
@RequestMapping("/api/article")
public class ApiArticleController {

    @Autowired
    private IArticleService articleService;

    @ApiOperation("查询文章详情接口")
    @ApiImplicitParam(name = "id", value = "文章ID", required = true)
    @GetMapping("/{id}") // localhost:8001/article/api/article/xxx
    public Result view(@PathVariable String id) {
        return articleService.findArticleAndLabelById(id);
    }

    @ApiImplicitParam(name = "id", value = "文章ID", required = true)
    @ApiOperation("更新文章浏览次数")
    @PutMapping("/viewCount/{id}") // /api/article/viewCount/{id}
    public Result updateViewCount(@PathVariable("id") String id) {
        return articleService.updateViewCount(id);
    }

    @ApiOperation("公开且已审核的文章列表接口")
    @PostMapping("/list") // /api/article/list
    public Result list(@RequestBody ArticleListREQ req) {
       return articleService.findListByLabelIdOrCategoryId(req);
    }
}
