package com.newland.blog.article.api;

import com.newland.blog.article.service.ICommentService;
import com.newland.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "评论管理API接口", description = "评论管理API接口，不需要通过身份认证就可以访问下面的接口")
@RestController
@RequestMapping("/api/comment")
public class ApiCommentController {

    @Autowired
    private ICommentService commentService;

    @ApiOperation("通过文章ID递归查询所有评论")
    @ApiImplicitParam(name = "articleId", value = "文章ID", required = true)
    @GetMapping("/list/{articleId}") // get 方式 /article/api/comment/list/1
    public Result findByArticleId(@PathVariable("articleId") String articleId) {
        return commentService.findByArticleId(articleId);
    }

}
