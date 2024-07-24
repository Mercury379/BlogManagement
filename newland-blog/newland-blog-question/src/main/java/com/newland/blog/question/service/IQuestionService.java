package com.newland.blog.question.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.blog.entities.Question;
import com.newland.blog.question.req.QuestionUserREQ;
import com.newland.blog.util.base.Result;

/**
 * <p>
 * 问题信息表 服务类
 * </p>
 */
public interface IQuestionService  extends IService<Question> {
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
    Result getQuestionTotal();

}
