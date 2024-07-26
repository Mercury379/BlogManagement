package com.example.newblognewsystem.system.controller;

import com.example.newblognewsystem.system.service.IRoleService;
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

@Api(value = "角色管理接口", description = "角色管理接口, 提供角色信息的增删改查")
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @ApiOperation(value = "新增角色")
    @PostMapping
    @Transactional // 添加事务注解以确保插入操作正确处理
    public Result addRole(@RequestBody Role role) {
        return roleService.addRole(role);
    }

    @ApiOperation(value = "为角色添加菜单")
    @PostMapping("/{roleId}/menus")
    @Transactional // 添加事务注解以确保插入操作正确处理
    public Result addMenusToRole(@PathVariable String roleId, @RequestBody List<String> menuIds) {
        return roleService.addMenusToRole(roleId,menuIds);
    }

    @ApiOperation(value = "删除角色及相关记录")
    @DeleteMapping("/{roleId}")
    @Transactional
    public Result deleteRole(@PathVariable String roleId) {
        return roleService.deleteRole(roleId);
    }
    @ApiOperation(value = "根据角色ID查询角色详情")
    @PostMapping("/{roleId}")
    @Transactional
    public Result getRole(@PathVariable String roleId) {
        return roleService.getRole(roleId);
    }
}