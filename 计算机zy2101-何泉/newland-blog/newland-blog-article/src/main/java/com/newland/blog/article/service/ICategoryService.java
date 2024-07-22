package com.newland.blog.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.blog.article.req.CategoryREQ;
import com.newland.blog.entities.Category;
import com.newland.blog.util.base.Result;

/**
 * 文章分类表-服务类（业务层）
 */

public interface ICategoryService extends IService<Category> {

    /**
     * 分页条件查询分类信息
     * @param req 条件
     * @return
     */
    Result queryPage(CategoryREQ req);

    /**
     * 查询正常状态的分类
     * @return
     */
    Result findAllNormal();

    /**
     * 查询正常状态的分类及分类下的所有标签
     * @return
     */
    Result findCategoryAndLabel();

}
