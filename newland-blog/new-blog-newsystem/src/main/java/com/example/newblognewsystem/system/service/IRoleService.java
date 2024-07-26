package com.example.newblognewsystem.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.blog.entities.Role;
import com.newland.blog.util.base.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IRoleService extends IService<Role> {
    /**
     * 新增角色
     */
    Result addRole(Role role);
    Result addMenusToRole(String roleId, List<String> menuIds);
    Result deleteRole(String roleId);
    Result getRole(String roleId);
}
