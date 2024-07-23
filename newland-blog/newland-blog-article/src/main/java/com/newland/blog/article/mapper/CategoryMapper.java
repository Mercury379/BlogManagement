package com.newland.blog.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newland.blog.entities.Category;

import java.util.List;

/**
 * 文章分类表 Mapper 接口
 * BaseMapper 接口是mybatis提供的，它里面提供很多常用的方法供我们直接使用
 */
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 查询正常状态的分类及分类下的所有标签
     * @return
     */
    List<Category> findCategoryAndLabel();

}
