package com.example.newblognewsystem.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.newblognewsystem.system.client.ArticleClient;
import com.example.newblognewsystem.system.mapper.RoleMapper;
import com.example.newblognewsystem.system.mapper.UserMapper;
import com.example.newblognewsystem.system.service.IRoleService;
import com.newland.blog.entities.Role;
import com.newland.blog.entities.User;
import com.newland.blog.util.base.Result;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Override
    public Result addRole(Role role) {
        // 检查是否已经存在相同 ID 的角色
        Role existingRole = baseMapper.findRoleById(role.getId());
        if (existingRole != null) {
            return Result.error("角色已存在");
        }

        // 插入新角色
        baseMapper.insert(role);
        return Result.ok(role.getId());
    }

    @Override
    public Result addMenusToRole(String roleId, List<String> menuIds) {
        // 检查角色是否存在
        Role role = baseMapper.selectById(roleId);
        if (role == null) {
            return Result.error("角色未找到");
        }
        // 插入角色与菜单的关联记录
        baseMapper.insertRoleMenus(roleId, menuIds);
        return Result.ok();
    }

    @Override
    public Result deleteRole(String roleId) {
        // 检查角色是否存在
        Role role = baseMapper.selectById(roleId);
        if (role == null) {
            return Result.error("角色未找到");
        }

        // 删除角色与菜单的关联记录
        baseMapper.deleteRoleMenus(roleId);
        // 删除角色
        baseMapper.deleteById(roleId);
        return Result.ok();
    }


}
