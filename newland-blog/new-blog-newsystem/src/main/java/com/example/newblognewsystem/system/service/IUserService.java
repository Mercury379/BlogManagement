package com.example.newblognewsystem.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.blog.entities.Role;
import com.newland.blog.entities.User;
import com.newland.blog.util.base.Result;
import com.newland.blog.util.enums.ArticleStatusEnum;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 */
public interface IUserService extends IService<User> {

    /**
     * 1.登录验证用户密码(需解密)
     * @param userName 用户名
     * @param password 密码
     * @return Result
     */
    Result login(String userName, String password);

    /**
     * 2.新增用户(密码需用加密算法,头像需上传至OSS)
     * @param user 用户
     * @return Result
     */
    Result saveUser(User user);

    Result findRolebyID(String id);



    /**
     * 6.修改状态：
     * @param id 用户id
     * @return
     */
    Result updateStatus(String id);

    /**
     * 8.分页查询用户列表
     * @return
     */
    Result queryPage();




}
