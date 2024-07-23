package com.newland.blog.article.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.newland.blog.article.req.LabelREQ;
import com.newland.blog.entities.Label;
import com.newland.blog.article.mapper.LabelMapper;
import com.newland.blog.article.service.ILabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.blog.util.base.Result;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 */
@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements ILabelService {

    @Override
    public Result queryPage(LabelREQ req) {
        // 条件分页查询
        IPage<Label> page = baseMapper.queryPage(req.getPage(), req);
        return Result.ok(page);
    }

    @Override
    public boolean updateById(Label label) {
        label.setUpdateDate(new Date());
        return super.updateById(label);
    }
}
