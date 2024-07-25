package com.newland.blog.question.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.blog.entities.Question;
import com.newland.blog.entities.QuestionLabel;
import com.newland.blog.question.mapper.QuestionLabelMapper;
import com.newland.blog.question.mapper.QuestionMapper;
import com.newland.blog.question.service.IQuestionLabelService;
import com.newland.blog.util.base.Result;
import com.newland.blog.util.enums.QuestionStatusEnum;
import org.springframework.stereotype.Service;

@Service
public class QuestionLabelServicempl extends ServiceImpl<QuestionLabelMapper, QuestionLabel> implements IQuestionLabelService {

    @Override
    public Result getLabelIds(String id){
        return Result.ok(baseMapper.selectLabelIdsByQuestionId(id));
    }
}
