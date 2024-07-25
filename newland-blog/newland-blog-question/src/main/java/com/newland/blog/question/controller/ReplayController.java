package com.newland.blog.question.controller;

import com.newland.blog.entities.Replay;
import com.newland.blog.question.req.QuestionReplayREQ;
import com.newland.blog.question.service.IReplayService;
import com.newland.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 回复信息表 前端控制器
 * </p>
 */
@Api(value = "回复管理接口", description = "回复管理接口, 提供回复的增删改查")
@RestController
@RequestMapping("/replay")
public class ReplayController {

    @Autowired
    private IReplayService replayService;

    // 1: 新增回复
    @ApiOperation("新增回复")
    @PostMapping
    public Result save(@RequestBody Replay replay) {
        return replayService.saveReplay(replay);
    }

    // 3: 根据回复ID删除回复
    @ApiImplicitParam(name = "id", value = "回复ID", required = true)
    @ApiOperation("根据回复ID删除回复")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") String id) {
        return replayService.deleteReplayById(id);
    }

    //2-2 :根据问题ID查询所有的回复（包括回复的回复）
    @ApiOperation("根据问题ID查询问题所有回复列表接口")
    @PostMapping("/replay")
    public Result findAllReplayByQuestionId(@RequestBody QuestionReplayREQ req) {
        return replayService.findAllReplayByQuestionId(req);
    }

    //2-3
    @ApiOperation("根据问题id获得回复个数")
    @GetMapping("/replay/total/{id}")
    public Result getReplaysByQuestionIdTotal(@PathVariable("id") String id) {
        return replayService.getReplaysByQuestionIdTotal(id);
    }
    @ApiImplicitParam(name = "id", value = "用户ID", required = true)
    @ApiOperation("根据用户ID统计该用户近6个月回复数")
    @GetMapping("/usermonth/{id}")
    public Result userMonthReplayTotal(@PathVariable("id") String id){
        return replayService.getUserMonthReplayTotal(id);
    }

}
