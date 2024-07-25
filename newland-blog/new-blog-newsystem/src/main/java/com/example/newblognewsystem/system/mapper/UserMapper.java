package com.example.newblognewsystem.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newland.blog.entities.Article;
import com.newland.blog.entities.Menu;
import com.newland.blog.entities.Role;
import com.newland.blog.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 3. 为用户赋予角色
     * @param id 用户id
     * @param role 角色名称
     * @return
     */
    boolean assignRoles(@Param("id") String id,
                             @Param("role") String role);

    /**
     * 3-2.修改指定用户的角色
     * @param id 用户id
     * @param role 角色名称
     * @return
     */
    boolean updateRole(@PathVariable("id") String id,
                       @PathVariable("role") String role);


    Role findRolebyID(String id);

    List<Menu> findMenuByUserID(String id);

}
