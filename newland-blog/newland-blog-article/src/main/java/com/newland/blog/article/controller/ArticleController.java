package com.newland.blog.article.controller;


import com.newland.blog.article.req.ArticleREQ;
import com.newland.blog.article.req.ArticleUserREQ;
import com.newland.blog.article.service.IArticleService;
import com.newland.blog.entities.Article;
import com.newland.blog.util.base.Result;
import com.newland.blog.util.enums.ArticleStatusEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 文章信息表 前端控制器
 * </p>
 */
@Api(value = "文章管理接口", description = "文章管理接口, 提供文章的增删改查")
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    @ApiOperation("根据文章标题和状态查询文章分页列表接口")
    @PostMapping("/search") // localhost:8001/article/article/search
    public Result search(@RequestBody ArticleREQ req) {
        return articleService.queryPage(req);
    }

    @ApiOperation("查询文章详情接口")
    @ApiImplicitParam(name = "id", value = "文章ID", required = true)
    @GetMapping("/{id}")
    public Result view(@PathVariable String id) {
        return articleService.findArticleAndLabelById(id);
    }


    @ApiOperation("修改文章信息接口")
    @PutMapping // put 请求 localhost:8001/article/article
    public Result update(@RequestBody Article article) {
        return articleService.updateOrSave(article);
    }

    @ApiOperation("新增文章信息接口")
    @PostMapping
    public Result save(@RequestBody Article article) {
        return articleService.updateOrSave(article);
    }

    @ApiImplicitParam(name = "id", value = "文章ID", required = true)
    @ApiOperation("删除文章接口")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") String id) {
        // 假删除，只是将状态更新
       return articleService.updateStatus(id, ArticleStatusEnum.DELETE);
    }

    @ApiImplicitParam(name = "id", value = "文章ID", required = true)
    @ApiOperation("审核通过接口")
    @GetMapping("/audit/success/{id}")
    public Result success(@PathVariable("id") String id) {
        // 审核通过
        return articleService.updateStatus(id, ArticleStatusEnum.SUCCESS);
    }

    @ApiImplicitParam(name = "id", value = "文章ID", required = true)
    @ApiOperation("审核不通过接口")
    @GetMapping("/audit/fail/{id}")
    public Result fail(@PathVariable("id") String id) {
        // 审核不通过
        return articleService.updateStatus(id, ArticleStatusEnum.FAIL);
    }

    @ApiOperation("根据用户ID查询公开或未公开的文章列表接口")
    @PostMapping("/user") // /article/user
    public Result findListByUserId(@RequestBody ArticleUserREQ req) {
        return articleService.findListByUserId(req);
    }

    @ApiImplicitParams({
       @ApiImplicitParam(name = "id", value = "文章ID", required = true),
       @ApiImplicitParam(name = "count", value = "点赞数", required = true)
    })
    @ApiOperation("更新点赞数")
    @PutMapping("/thumb/{id}/{count}")
    public Result updataThumhup(@PathVariable("id") String id,
                                @PathVariable("count") int count) {
        return articleService.updateThumhup(id, count);
    }

    @ApiOperation("统计审核通过且公开的文章总记录数")
    @GetMapping("/total")
    public Result getArticleTotal() {
        return articleService.getArticleTotal();
    }

    @ApiOperation("统计各分类下的文章数")
    @GetMapping("/category/total")
    public Result categoryTotal() {
        return articleService.selectCategoryTotal();
    }

    @ApiOperation("统计近6个月发布的文章数")
    @GetMapping("/month/total")
    public Result monthArticleTotal() {
        return articleService.selectMonthArticleTotal();
    }
    @ApiImplicitParam(name = "id", value = "用户ID", required = true)
    @ApiOperation("根据用户ID统计该用户近6个月发布的文章数")
    @GetMapping("/usermonth/{id}")
    public Result userMonthArticleTotal(@PathVariable("id") String id){
        return articleService.getUserMonthArticleTotal(id);
    }
}
