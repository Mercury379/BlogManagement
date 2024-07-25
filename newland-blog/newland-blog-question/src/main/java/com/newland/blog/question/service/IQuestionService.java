package com.newland.blog.question.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.blog.entities.Question;
import com.newland.blog.question.req.QuestionREQ;
import com.newland.blog.question.req.QuestionReplayREQ;
import com.newland.blog.question.req.QuestionUserREQ;
import com.newland.blog.util.base.Result;
import com.newland.blog.util.enums.QuestionStatusEnum;

import java.util.List;

/**
 * <p>
 * 问题信息表 服务类
 * </p>
 */
public interface IQuestionService  extends IService<Question> {

    /**
     * 修改或新增问题
     * @param question
     * @return
     */
    Result updateOrSave(Question question);

    /**
     * 修改状态：
     * @param id 问题id
     * @param statusEnum 状态枚举类
     * @return
     */
    Result updateStatus(String id, QuestionStatusEnum statusEnum);

    /**
     * 根据问题id更新点赞数
     * @param id 问题id
     * @param count 点赞接收+1，取消点赞 -1
     * @return
     */
    Result updateThumhup(String id, int count);

    /**
     * 根据用户ID查询问题列表
     * @param req 包含用户ID的请求对象
     * @return 查询结果
     */
    Result findListByUserId(QuestionUserREQ req);

    /**
     * 查询提问总记录
     * @return 查询结果
     */
    Result queryPage(QuestionREQ req);
    /**
     * 为问题新增标签
     * @param questionId 问题ID
     * @param labelIds 标签集合
     * @return
     */
    Result addQuestionLabel(String questionId, List<String> labelIds);
    /**
     * 根据用户id统计该用户近6个月发表的问题数
     */
    Result getUserMonthQuestionTotal(String userId);
}
