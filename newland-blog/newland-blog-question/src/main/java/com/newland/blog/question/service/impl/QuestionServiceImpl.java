package com.newland.blog.question.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.blog.entities.Question;
import com.newland.blog.question.mapper.QuestionMapper;
import com.newland.blog.question.req.QuestionUserREQ;
import com.newland.blog.question.service.IQuestionService;
import com.newland.blog.util.base.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 问题信息表 服务实现类
 * </p>
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper,Question> implements IQuestionService{

    @Override
    public Result findListByUserId(QuestionUserREQ req) {
        if (StringUtils.isEmpty(req.getUserId())) {
            return Result.error("无效用户信息");
        }

        QueryWrapper<Question> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", req.getUserId());

        // 排序
        wrapper.orderByDesc("update_date");

        IPage<Question> page = baseMapper.selectPage(req.getPage(), wrapper);

        return Result.ok(page);
    }

    @Override
    public Result getQuestionTotal() {
        QueryWrapper<Question> wrapper = new QueryWrapper<>();
        // 查询所有问题总记录
        List<Question> totalList = baseMapper.selectList(wrapper);
        return Result.ok(totalList);
    }




}
