package com.example.newblognewsystem.system.mapper;


import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.newland.blog.article.req.ArticleListREQ;
import com.newland.blog.entities.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 通过文章id查询文章详情及标签
     * @param id
     * @return
     */
    Role findArticleAndLabelById(String id);

    /**


    /**
     * 通过用户id查询角色信息
     * @param id
     * @return
     */
    Role findRoleByUserId(String id);

}

