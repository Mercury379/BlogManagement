package com.newland.blog.question.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.blog.entities.Question;
import com.newland.blog.entities.Replay;
import com.newland.blog.question.mapper.ReplayMapper;
import com.newland.blog.question.req.QuestionReplayREQ;
import com.newland.blog.question.req.ReplayUserREQ;
import com.newland.blog.question.service.IReplayService;
import com.newland.blog.util.base.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
        // 使用递归方法删除指定回复及其所有子回复
        deleteReplayAndChildren(replyId);
        return Result.ok();
    }

    private void deleteReplayAndChildren(String replyId) {
        // 先删除当前回复
        baseMapper.deleteReplayById(replyId);

        // 查找所有子回复
        QueryWrapper<Replay> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", replyId);
        List<Replay> childReplays = baseMapper.selectList(wrapper);

        // 递归删除子回复
        for (Replay childReplay : childReplays) {
            deleteReplayAndChildren(childReplay.getId());
        }
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
    @Override
    public Result getUserMonthReplayTotal(String userId) {
        List<Map<String, Object>> maps = baseMapper.getUserMonthReplayTotal(userId);
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
        data.put("replayTotalList", articleTotalList);
        return Result.ok(data);
    }

    @Override
    public Result getReplay(String id) {
        if(StringUtils.isEmpty(id)){
            return Result.error("回复不存在");
        }
        Replay replay=baseMapper.selectById(id);
        return Result.ok(replay);
    }

    @Override
    public Result getUserReplay(ReplayUserREQ req) {
        QueryWrapper<Replay> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(req.getUserId())){
            wrapper.eq("user_id", req.getUserId());
        }
        IPage<Replay> replays = baseMapper.selectPage(req.getPage(),wrapper);
        return Result.ok(replays);
    }
}

