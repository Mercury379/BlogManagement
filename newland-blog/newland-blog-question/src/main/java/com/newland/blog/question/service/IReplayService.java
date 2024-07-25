package com.newland.blog.question.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.blog.entities.Replay;
import com.newland.blog.question.req.QuestionReplayREQ;
import com.newland.blog.util.base.Result;

/**
 * <p>
 * 回复信息表 服务类
 * </p>
 */
public interface IReplayService extends IService<Replay> {

    /**
     * 新增回复
     * @param replay 回复对象
     * @return 操作结果
     */
    Result saveReplay(Replay replay);
    /**
     * 根据回复ID删除回复
     * @param id 回复ID
     * @return 操作结果
     */
    Result deleteReplayById(String id);
    /**
     * 根据问题ID查询对应问题下所有回复
     */
    public Result findAllReplayByQuestionId(QuestionReplayREQ req);
    /**
     * 根据问题ID查询回复个数
     * @param questionId 问题ID
     * @return 查询结果
     */
    Result getReplaysByQuestionIdTotal(String questionId);
}
