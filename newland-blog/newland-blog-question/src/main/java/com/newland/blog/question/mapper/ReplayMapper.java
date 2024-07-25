package com.newland.blog.question.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newland.blog.entities.Replay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReplayMapper extends BaseMapper<Replay> {

    // 插入新的回复
    int saveReplay(Replay replay);

    // 通过ID删除回复
    int deleteReplayById(String replyId);

    // 根据ID查询回复
    Replay selectById(String replyId);
    /**
     * 查询问题下所有回复个数
     * @param questionId 问题id
     * @return 回复个数
     */
    int getNumOfReplaysByQuestionId(String questionId);
    List<Map<String,Object>> getUserMonthReplayTotal(@Param("userId") String userId);
}
