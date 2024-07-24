package com.newland.blog.question.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newland.blog.entities.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 问题信息表 Mapper 接口
 * </p>
 */
public interface QuestionMapper extends BaseMapper<Question> {

    /**
     * 通过问题id删除问题标签表数据
     * @param questionId
     * @return
     */
    boolean deleteQuestionLabel(@Param("questionId") String questionId);

    /**
     * 新增问题标签中间表数据
     * @param questionId 问题id
     * @param labelIds 标签id集合
     * @return
     */
    boolean saveQuestionLabel(@Param("questionId") String questionId,
                              @Param("labelIds") List<String> labelIds);


}
