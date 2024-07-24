package com.newland.blog.question.controller;


import com.newland.blog.question.req.QuestionUserREQ;
import com.newland.blog.question.service.IQuestionService;
import com.newland.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IQuestionService questionService;

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

}
