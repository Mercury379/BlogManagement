package com.newland.blog.question.controller;


import com.newland.blog.entities.Question;
import com.newland.blog.question.req.QuestionUserREQ;
import com.newland.blog.question.service.ArticleClient;
import com.newland.blog.question.service.IQuestionService;
import com.newland.blog.util.base.Result;
import com.newland.blog.util.enums.QuestionStatusEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 问题信息表 前端控制器
 * </p>
 */
@Api(value = "问答管理接口", description = "问答管理接口, 提供问答的增删改查")
@RestController
@RequestMapping("/question")
public class QuestionController {
    /**
     * 完成问答微服务的相关业务开发
     * 1：修改问题信息接口
     * 2：新增问题信息接口
     * 3: 删除问题信息接口
     * 4: 更新点赞数
     * 5: 根据用户id查询问题列表
     * 6: 查询提问总记录
     * 7: 根据问题ID 远程调用文章微服务查询文章详细信息
     */
    /**
     * 1. 为问题新增标签
     * 2. 查询问题下所有的回复（包括回复的回复）
     * 3. 根据问题ID统计问题下所有的回复数，并修改数据库中的count_view
     */

    @Autowired
    private IQuestionService questionService;

    @Autowired
    ArticleClient articleClient; // feign远程调用(xhq)

    //1：修改问题信息接口
    @ApiOperation("修改问题信息接口")
    @PutMapping // put 请求 localhost:8001/article/article
    public Result update(@RequestBody Question question) {
        return questionService.updateOrSave(question);
    }

    // 2：新增问题信息接口
    @ApiOperation("新增问题信息接口")
    @PostMapping
    public Result save(@RequestBody Question question) {
        return questionService.updateOrSave(question);
    }

    //3: 删除问题信息接口
    @ApiImplicitParam(name = "id", value = "问题ID", required = true)
    @ApiOperation("删除问题接口")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") String id) {
        // 假删除，只是将状态更新
        return questionService.updateStatus(id, QuestionStatusEnum.DELETE);
    }

    //4: 更新点赞数
    @ApiOperation("更新点赞数")
    @PutMapping("/thumb/{id}/{count}")
    public Result updataThumhup(@PathVariable("id") String id,
                                @PathVariable("count") int count) {
        return questionService.updateThumhup(id, count);
    }

    //5: 根据用户id查询问题列表
    @ApiOperation("根据用户ID查询问题列表接口")
    @PostMapping("/user")
    public Result findListByUserId(@RequestBody QuestionUserREQ req) {
        return questionService.findListByUserId(req);
    }
    //6: 查询提问总记录
    @ApiOperation("查询提问总记录")
    @GetMapping("/total")
    public Result getQuestionTotal() {
        return questionService.getQuestionTotal();
    }

    //7 : 根据问题ID 远程调用文章微服务查询文章详细信息
    @ApiOperation("根据问题ID查询文章详细信息")
    @GetMapping("/feign/{id}")
    public Result findArticleById(@PathVariable("id") String id) {
        //todo:根据问题id，查出文章id


        //根据文章id，远程调用文章微服务查询文章详细信息
        return articleClient.findArticleById(id);
    }





}
