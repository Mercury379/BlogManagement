package com.newland.blog.question.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newland.blog.entities.Replay;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplayMapper extends BaseMapper<Replay> {

    // 插入新的回复
    int saveReplay(Replay replay);

    // 通过ID删除回复
    int deleteReplayById(String replyId);

    // 根据ID查询回复
    Replay selectById(String replyId);
}
