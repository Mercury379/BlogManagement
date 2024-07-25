package com.example.newblognewsystem.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.newblognewsystem.system.mapper.UserMapper;
import com.example.newblognewsystem.system.service.IUserService;
import com.example.newblognewsystem.system.util.PasswordUtil;
import com.newland.blog.entities.Article;
import com.newland.blog.entities.Role;
import com.newland.blog.entities.User;
import com.newland.blog.util.base.Result;
import com.newland.blog.util.enums.ArticleStatusEnum;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements IUserService {

    @Override
    public Result login(String userName, String password) {
        // 1.登录验证用户密码
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", userName);
        User user = baseMapper.selectOne(wrapper);
        if (user == null) {
            return Result.error("用户不存在");
        } else if (user.getIsAccountNonExpired() == 0) {
            return Result.error("账号已过期");
        } else if (user.getIsAccountNonLocked() == 0) {
            return Result.error("账号已被锁定");
        } else if (user.getIsCredentialsNonExpired() == 0) {
            return Result.error("密码已过期");
        } else if (user.getIsEnabled() == 0) {
            return Result.error("账号已被禁用");
        } else {
            // 对密码进行解密，使用BCryptPasswordEncoder单向加密算法
            if (PasswordUtil.matchPassword(password, user.getPassword())) {
                return Result.ok(user);
            } else {
                return Result.error("密码错误");
            }
        }
    }

    // 2.新增用户(密码需用加密算法,头像需上传至OSS)
    @Override
    public Result saveUser(User user) {
        // 对密码进行加密，使用BCryptPasswordEncoder单向加密算法
        String encryptedPassword = PasswordUtil.encryptPassword(user.getPassword());
        user.setPassword(encryptedPassword);

        baseMapper.insert(user);
        return Result.ok();
    }

    //3. 为用户赋予角色
    @Override
    public Result assignRoles(String id,String role){
        baseMapper.assignRoles(id,role);
        return Result.ok();
    }

    //3-2.修改指定用户的角色
    @Override
    public Result updateRole(String id, String role){
        baseMapper.updateRole(id,role);
        return Result.ok();
    }


    //4. 根据用户ID返回菜单
    @Override
    public Result findMenuByUserID(String id) {
        return Result.ok(baseMapper.findMenuByUserID(id));
    }

    //5. 根据用户ID返回其角色的详细信息
    @Override
    public Result findRolebyID(String id) {
        Role role=baseMapper.findRolebyID(id);
        return Result.ok(role);
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

    //8.分页查询用户列表
    @Override
    public Result queryPage() {
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.orderByDesc("update_date");
        Page<User> userPage=new Page<>();
        IPage<User> page = baseMapper.selectPage(userPage, wrapper);
        return Result.ok(page);
    }


}
