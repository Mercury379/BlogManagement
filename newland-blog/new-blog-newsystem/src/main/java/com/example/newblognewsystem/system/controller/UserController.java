package com.example.newblognewsystem.system.controller;

import com.example.newblognewsystem.system.service.IUserService;
import com.newland.blog.util.base.Result;
import com.newland.blog.util.enums.ArticleStatusEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 */
@Api(value = "用户管理接口", description = "用户管理接口, 提供用户信息的增删改查")
@RestController
@RequestMapping("/user")
public class UserController {
    /**1. 登录验证用户密码(需解密)
     * 2. 新增用户(密码需用加密算法,头像需上传至OSS)
     * 3. 为用户赋予角色
     * 4. 根据用户ID查询其对应的所有菜单列表详细信息
     * 5. 根据用户ID返回其角色的详细信息
     * 6. 删除用户(假删除）
     * 7. 根据ID，查询用户的详细信息
     * 8. 返回所有用户的列表
     * 9. 修改用户信息
     * 10. 根据用户ID查询用户所有评论的历史记录(需远程调用)
     * 11. 根据用户ID查询用户发表的所有文章(需远程调用)
     * 12. 根据用户ID查询用户提出的所有问题(需远程调用)
     * 13. 统计用户近6个月发表的文章数(需远程调用)
     * 14. 统计用户近6个月发表的问题数(需远程调用)
     * 15. 统计用户近6个月发表的评论数(需远程调用)
     */

    @Autowired
    private IUserService userService;

    @ApiOperation("登录验证用户密码(需解密)")
    @PostMapping
    public Result login() {
        return null;
    }

    @ApiImplicitParam(
            name = "id",
            value = "用户ID",
            required = true
    )

    //3. 为用户赋予角色

    //4. 根据用户ID查询其对应的所有菜单列表详细信息

    //5. 根据用户ID返回其角色的详细信息
    @ApiOperation("根据用户ID返回其角色的详细信息")
    @GetMapping({"/{id}"})
    public Result findRolebyID(@PathVariable("id") String id) {
        return userService.findRolebyID(id);
    }

    //6. 删除用户(假删除）
    @ApiImplicitParam(name = "id", value = "用户ID", required = true)
    @ApiOperation("删除用户接口")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") String id) {
        // 假删除，只是将状态更新
        return userService.updateStatus(id);
    }

    //7. 根据ID，查询用户的详细信息
    @ApiImplicitParam(name = "id", value = "用户ID", required = true)
    @ApiOperation("查询用户详情接口")
    @GetMapping("/view/{id}")
    public Result view(@PathVariable("id") String id) {
        return Result.ok( userService.getById(id) );
    }

    //8. 返回所有用户的列表
    @ApiOperation("返回所有用户的列表")
    @PostMapping("/search")
    public Result search() {
        return userService.queryPage();
    }

    //9. 修改用户信息

}
