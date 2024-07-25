package com.newland.blog.question.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newland.blog.entities.QuestionLabel;

import java.util.List;

public interface QuestionLabelMapper extends BaseMapper<QuestionLabel> {
    List<String> selectLabelIdsByQuestionId(String id);
}
