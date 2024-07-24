package com.newland.blog.question.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.blog.entities.Article;
import com.newland.blog.entities.Question;
import com.newland.blog.entities.Replay;
import com.newland.blog.question.mapper.QuestionMapper;
import com.newland.blog.question.req.QuestionUserREQ;
import com.newland.blog.question.service.IQuestionService;
import com.newland.blog.util.base.Result;
import com.newland.blog.util.enums.QuestionStatusEnum;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 问题信息表 服务实现类
 * </p>
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper,Question> implements IQuestionService{

    @Transactional // 事务管理
    @Override
    public Result updateOrSave(Question question) {
        // 1. 如果id不为空，则是更新操作
        if(StringUtils.isNotEmpty(question.getId())) {
            // 更新：先删除问题中间表数据，再新增到中间表
            baseMapper.deleteQuestionLabel(question.getId());
            // 更新：将更新时间设置当前时间
            question.setUpdateDate(new Date());
        }

        // 更新或保存问题信息（新增数据后，会将这条新增数据的主键id值放到id属性中）
        super.saveOrUpdate(question);

        // 新增标签数据到问题标签中间表中
        if(CollectionUtils.isNotEmpty(question.getLabelIds())) {
            baseMapper.saveQuestionLabel(question.getId(), question.getLabelIds());
        }

        // 返回问题id
        return Result.ok(question.getId());
    }

    @Override
    public Result updateStatus(String id, QuestionStatusEnum statusEnum) {
        // 先查询当前数据库的数据
        Question question = baseMapper.selectById(id);
        // 将状态值 更新
        question.setStatus(statusEnum.getCode());
        question.setUpdateDate(new Date());
        baseMapper.updateById(question);
        return Result.ok();
    }

    @Override
    public Result updateThumhup(String id, int count) {
        if(count != -1 && count != 1) {
            return Result.error("无效操作");
        }

        if(StringUtils.isEmpty(id)) {
            return Result.error("无效操作");
        }

        // 查询这篇文章现有数据，查询到后，将点赞数进行更新
        Question question = baseMapper.selectById(id);
        if(question == null) {
            return Result.error("文章不存在");
        }

        if(question.getThumhup() <= 0 && count == -1) {
            return Result.error("无效操作");
        }
        // 更新点赞数
        question.setThumhup( question.getThumhup() + count );
        baseMapper.updateById(question);

        return Result.ok();
    }

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

    @Override
    @Transactional
    public Result addQuestionLabel(String questionId, List<String> labelIds) {
        if (StringUtils.isEmpty(questionId) || CollectionUtils.isEmpty(labelIds)) {
            return Result.error("问题ID或标签ID不能为空");
        }

        // 删除现有的问题与标签的关联关系
        baseMapper.deleteQuestionLabel(questionId);

        // 保存新的问题与标签的关联关系
        baseMapper.saveQuestionLabel(questionId, labelIds);

        return Result.ok("问题标签添加成功");
    }

    @Override
    public Result findAllReplayByQuestionId(String questionId) {
        Question question = baseMapper.selectById(questionId);
        if(question == null) {
            return Result.error("无效操作");
        }
        List<Replay> replays = baseMapper.findReplaysByQuestionId(questionId);
        return Result.ok(replays);
    }

    @Override
    public Result getReplaysByQuestionIdTotal(String questionId) {
        Question question = baseMapper.selectById(questionId);
        if(question == null) {
            return Result.error("无效操作");
        }
        int num = baseMapper.getNumOfReplaysByQuestionId(questionId);
        return Result.ok(num);
    }

    @Override
    public Result getUserMonthQuestionTotal(String userId) {
        List<Map<String, Object>> maps = baseMapper.getUserMonthQuestionTotal(userId);
        // 将年月提取到集合中
        List<Object> yearMonthList = new ArrayList<>();
        // 将每个月的文章数提取到集合中
        List<Object> articleTotalList = new ArrayList<>();

        for(Map<String, Object> map: maps) {
            yearMonthList.add ( map.get("year_month") );
            articleTotalList.add( map.get("total") );
        }

        // 封装响应的data数据
        Map<String, Object> data = new HashMap<>();
        data.put("yearMonthList", yearMonthList);
        data.put("aritcleTotalList", articleTotalList);
        return Result.ok(data);
    }


}
