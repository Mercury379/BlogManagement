package com.example.newblognewsystem.system.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.newblognewsystem.system.mapper.UserMapper;
import com.example.newblognewsystem.system.service.IUserService;
import com.newland.blog.entities.Article;
import com.newland.blog.entities.Role;
import com.newland.blog.entities.User;
import com.newland.blog.util.base.Result;
import com.newland.blog.util.enums.ArticleStatusEnum;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements IUserService {

    @Override
    public Result login(String userName, String password) {
        return null;
    }

    @Override
    public Role findRolebyID(String id) {
        baseMapper.findRolebyID(id);
        return baseMapper.findRolebyID(id);
    }

    //6. 删除用户(假删除）
    @Override
    public Result updateStatus(String id) {
        // 先查询当前数据库的数据
        User user = baseMapper.selectById(id);
        // 将状态值 更新
        user.setIsEnabled(0);
        user.setUpdateDate(new Date());
        baseMapper.updateById(user);
        return Result.ok();
    }


}
