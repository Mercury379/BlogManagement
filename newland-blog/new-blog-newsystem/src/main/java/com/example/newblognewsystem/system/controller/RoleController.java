package com.example.newblognewsystem.system.controller;

import com.newland.blog.entities.Role;
import com.newland.blog.util.base.Result;
import com.example.newblognewsystem.system.mapper.RoleMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Api(tags = "角色管理")
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleMapper roleMapper;

    @ApiOperation(value = "新增角色")
    @PostMapping
    @Transactional // 添加事务注解以确保插入操作正确处理
    public Result addRole(@RequestBody Role role) {
        // 检查是否已经存在相同 ID 的角色
        Role existingRole = roleMapper.findRoleById(role.getId());
        if (existingRole != null) {
            return Result.error("角色已存在");
        }

        // 插入新角色
        roleMapper.insert(role);
        return Result.ok(role);
    }

    @ApiOperation(value = "为角色添加菜单")
    @PostMapping("/{roleId}/menus")
    @Transactional // 添加事务注解以确保插入操作正确处理
    public Result addMenusToRole(@PathVariable String roleId, @RequestBody List<String> menuIds) {
        // 检查角色是否存在
        Role role = roleMapper.selectById(roleId);
        if (role == null) {
            return Result.error("角色未找到");
        }

        String id = UUID.randomUUID().toString();

        // 插入角色与菜单的关联记录
        roleMapper.insertRoleMenus(id, roleId, menuIds);
        return Result.ok();
    }

    @ApiOperation(value = "删除角色及相关记录")
    @DeleteMapping("/{roleId}")
    @Transactional
    public Result deleteRole(@PathVariable String roleId) {
        // 检查角色是否存在
        Role role = roleMapper.selectById(roleId);
        if (role == null) {
            return Result.error("角色未找到");
        }

        // 删除角色与菜单的关联记录
        roleMapper.deleteRoleMenus(roleId);
        // 删除角色
        roleMapper.deleteById(roleId);
        return Result.ok();
    }
}