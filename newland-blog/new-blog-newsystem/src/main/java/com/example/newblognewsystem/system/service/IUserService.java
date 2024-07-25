package com.example.newblognewsystem.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.blog.entities.Role;
import com.newland.blog.entities.User;
import com.newland.blog.util.base.Result;

public interface IUserService extends IService<User> {

    /**
     * 1.登录验证用户密码(需解密)
     * @param userName 用户名
     * @param password 密码
     * @return Result
     */
    Result login(String userName, String password);

    Role findRoleByID(String id);

    Result findMenuByUserID(String id);

    /**
     * 2.新增用户(密码需用加密算法,头像需上传至OSS)
     *
     */



}
