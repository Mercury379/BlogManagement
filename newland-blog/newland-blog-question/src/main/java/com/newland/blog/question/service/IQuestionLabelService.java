package com.newland.blog.question.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.blog.entities.QuestionLabel;
import com.newland.blog.util.base.Result;


public interface IQuestionLabelService extends IService<QuestionLabel>{

    public Result getLabelIds(String id);
}
