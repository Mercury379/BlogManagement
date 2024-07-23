package com.newland.blog.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.blog.article.mapper.CategoryMapper;
import com.newland.blog.article.req.CategoryREQ;
import com.newland.blog.article.service.ICategoryService;
import com.newland.blog.entities.Category;
import com.newland.blog.util.base.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 文章分类表服务实现 类
 */
@Service // 一定不要少了
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
        implements ICategoryService {

    @Override
    public Result queryPage(CategoryREQ req) {
        // 查询条件对象
        QueryWrapper<Category> wrapper = new QueryWrapper();
        // 分类名称、状态
        if( StringUtils.isNotEmpty(req.getName()) ){
            wrapper.like("name", req.getName());
        }

        if(req.getStatus() != null) {
            wrapper.eq("status", req.getStatus());
        }
        
        wrapper.orderByDesc("status").orderByAsc("sort");

        // 第1个参数是Page分页对象，第2个参数查询条件
        IPage<Category> data = baseMapper.selectPage(req.getPage(), wrapper);
        return Result.ok(data);
    }



    @Override
    public boolean updateById(Category category) {
        // 设置更新时间
        category.setUpdateDate(new Date());
        return super.updateById(category);
    }

    @Override
    public Result findAllNormal() {
        QueryWrapper<Category> wrapper = new QueryWrapper();
        wrapper.eq("status", 1); // 1是正常，0是停用
        List<Category> categories = baseMapper.selectList(wrapper);
        return Result.ok(categories);
    }

    @Override
    public Result findCategoryAndLabel() {
        return Result.ok( baseMapper.findCategoryAndLabel() );
    }


}
