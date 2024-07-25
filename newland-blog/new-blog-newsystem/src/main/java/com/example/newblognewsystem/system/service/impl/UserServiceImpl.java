package com.example.newblognewsystem.system.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.newblognewsystem.system.mapper.UserMapper;
import com.example.newblognewsystem.system.service.IUserService;
import com.newland.blog.entities.Role;
import com.newland.blog.entities.User;
import com.newland.blog.util.base.Result;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements IUserService {

    @Override
    public Result login(String userName, String password) {
        return null;
    }

    @Override
    public Role findRoleByID(String id) {
        baseMapper.findRoleByID(id);
        return baseMapper.findRoleByID(id);
    }

    @Override
    public Result findMenuByUserID(String id) {
        return Result.ok(baseMapper.findMenuByUserID(id));
    }


}
