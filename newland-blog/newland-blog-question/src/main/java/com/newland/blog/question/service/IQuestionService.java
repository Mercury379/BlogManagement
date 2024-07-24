package com.newland.blog.question.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.blog.entities.Question;
import com.newland.blog.question.req.QuestionUserREQ;
import com.newland.blog.util.base.Result;
import com.newland.blog.util.enums.QuestionStatusEnum;

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
     * 根据文章id更新点赞数
     * @param id 文章id
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
    Result getQuestionTotal();

}
