package com.newland.blog.question.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.blog.entities.Replay;
import com.newland.blog.question.mapper.ReplayMapper;
import com.newland.blog.question.service.IReplayService;
import com.newland.blog.util.base.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
        // 生成唯一的ID，如果未提供
        if (StringUtils.isEmpty(replay.getId())) {
            replay.setId(generateUniqueId());
        } else if (checkIdExists(replay.getId())) {
            return Result.error("ID 已经存在");
        }

        // 设置创建时间
        replay.setCreateDate(new Date());
        // 设置 parentId 为 -1，表示正常回答
        replay.setParentId("-1");

        // 使用 baseMapper 保存数据
        boolean result = baseMapper.saveReplay(replay) > 0;
        return result ? Result.ok() : Result.error("保存回复失败");
    }

    @Transactional
    @Override
    public Result saveReplyToReplay(Replay replay) {
        // 生成唯一的ID，如果未提供
        if (StringUtils.isEmpty(replay.getId())) {
            replay.setId(generateUniqueId());
        } else if (checkIdExists(replay.getId())) {
            return Result.error("ID 已经存在");
        }

        // 查询父类回复是否存在
        Replay parentReplay = baseMapper.selectById(replay.getParentId());

        // 如果父类回复不存在，则设置 parentId 为 -1
        if (parentReplay == null) {
            replay.setParentId("-1");
        }

        replay.setCreateDate(new Date());
        // 使用 baseMapper 保存数据
        baseMapper.saveReplay(replay);
        return Result.ok();
    }

    @Transactional
    @Override
    public Result deleteReplayById(String replyId) {
        // 使用 checkIdExists 方法检查回复是否存在
        if (!checkIdExists(replyId)) {
            // 如果不存在，返回相应的提示信息
            return Result.error("ID 不存在");
        }

        // 使用 baseMapper 删除指定回复
        baseMapper.deleteReplayById(replyId);
        return Result.ok();
    }

    /**
     * 检查ID是否存在
     * @param id 回复ID
     * @return 如果存在则返回 true，否则返回 false
     */
    private boolean checkIdExists(String id) {
        return baseMapper.selectById(id) != null;
    }

    /**
     * 生成唯一的ID
     * @return 唯一ID
     */
    private String generateUniqueId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

