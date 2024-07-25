package com.newland.blog.question.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.blog.entities.Question;
import com.newland.blog.entities.Replay;
import com.newland.blog.question.mapper.ReplayMapper;
import com.newland.blog.question.req.QuestionReplayREQ;
import com.newland.blog.question.service.IReplayService;
import com.newland.blog.util.base.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 回答信息表 服务实现类
 * </p>
 */
@Service
public class ReplayServiceImpl extends ServiceImpl<ReplayMapper, Replay> implements IReplayService {

    @Transactional
    @Override
    public Result saveReplay(Replay replay) {
        // 如果父类回复不存在，说明是对问题的回复，则设置 parentId 为 -1
        //否则是对回复的回复
        if (StringUtils.isEmpty(replay.getParentId())) {
            replay.setParentId("-1");
        }else{
            if(baseMapper.selectById(replay.getParentId())==null){
                return Result.error("无效回复");
            }
        }
        replay.setCreateDate(new Date());
        // 使用 baseMapper 保存数据
        baseMapper.saveReplay(replay);
        return Result.ok(replay.getId());
    }

    @Transactional
    @Override
    public Result deleteReplayById(String replyId) {
        // 使用 baseMapper 删除指定回复
        baseMapper.deleteReplayById(replyId);
        return Result.ok();
    }
    public Result findAllReplayByQuestionId(QuestionReplayREQ req) {
        if(StringUtils.isEmpty(req.getQuestionId())){
            Result.error("无效问题信息");
        }
        QueryWrapper<Replay> wrapper = new QueryWrapper();
        wrapper.eq("question_id",req.getQuestionId());
        //wrapper.orderByAsc("update_date");
        IPage<Replay> replays = baseMapper.selectPage(req.getPage(),wrapper);
        return Result.ok(replays);
    }
    @Override
    public Result getReplaysByQuestionIdTotal(String questionId) {
        if(StringUtils.isEmpty(questionId)) {
            return Result.error("无效操作");
        }
        int num = baseMapper.getNumOfReplaysByQuestionId(questionId);
        return Result.ok(num);
    }
}

