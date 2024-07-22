package com.newland.blog.article.service;

import com.newland.blog.article.req.LabelREQ;
import com.newland.blog.entities.Label;
import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.blog.util.base.Result;

/**
 * <p>
 * 标签表 服务类
 * </p>
 */
public interface ILabelService extends IService<Label> {

    /**
     * 条件分页查询标签列表
     * @param req
     * @return
     */
    Result queryPage(LabelREQ req);
}
