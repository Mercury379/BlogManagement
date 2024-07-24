package com.newland.blog.question.controller;

import com.newland.blog.entities.Replay;
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

    // 1: 对指定问题新增回复
    @ApiOperation("对指定问题新增回复")
    @PostMapping
    public Result save(@RequestBody Replay replay) {
        return replayService.saveReplay(replay);
    }

    // 2: 对问题下的回复再进行回复
    @ApiOperation("对问题下的回复再进行回复")
    @PostMapping("/reply")
    public Result saveReplyToReplay(@RequestBody Replay replay) {
        return replayService.saveReplyToReplay(replay);
    }

    // 3: 根据回复ID删除回复
    @ApiImplicitParam(name = "id", value = "回复ID", required = true)
    @ApiOperation("根据回复ID删除回复")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") String id) {
        return replayService.deleteReplayById(id);
    }
}
