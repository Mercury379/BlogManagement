package com.example.newblognewsystem.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newland.blog.entities.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据角色ID查询角色
     * @param id 角色ID
     * @return 角色对象
     */
    Role findRoleById(@Param("id") String id);

    /**
     * 插入角色与菜单的关联记录
     * @param roleId 角色ID
     * @param menuIds 菜单ID列表
     */
    void insertRoleMenus(@Param("id")String id, @Param("roleId") String roleId, @Param("menuIds") List<String> menuIds);

    /**
     * 删除角色与菜单的关联记录
     * @param roleId 角色ID
     */
    void deleteRoleMenus(@Param("roleId") String roleId);
}

