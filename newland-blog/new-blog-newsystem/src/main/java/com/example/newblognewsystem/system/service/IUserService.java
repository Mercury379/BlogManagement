package com.example.newblognewsystem.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.newblognewsystem.system.req.ArticleUserREQ;
import com.example.newblognewsystem.system.req.QuestionUserREQ;
import com.example.newblognewsystem.system.req.ReplayUserREQ;
import com.newland.blog.entities.Menu;
import com.newland.blog.entities.Role;
import com.newland.blog.entities.User;
import com.newland.blog.util.base.Result;
import com.newland.blog.util.enums.ArticleStatusEnum;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

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
     * 4.查找菜单
     * @param id 用户
     * @return Result
     */
    Result findMenuByUserID(String id);


    /**
     * 3. 为用户赋予角色
     * @param id 用户id
     * @param role 角色名称
     * @return Result
     */
    Result assignRoles(String id,String role);

    /**
     * 3-2.修改指定用户的角色
     * @param id 用户id
     * @param role 角色名称
     * @return Result
     */
    Result updateRole(String id, String role);

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
    Result userMonthQuestionTotal(@PathVariable("id") String id);
    Result userMonthArticleTotal(@PathVariable("id") String id);
    Result userMonthReplayTotal(@PathVariable("id") String id);


    Result findArticleListByUserId(ArticleUserREQ req);

    Result findReplayListByUserId(ReplayUserREQ req);

    Result findQuestionListByUserId(QuestionUserREQ req);
}
