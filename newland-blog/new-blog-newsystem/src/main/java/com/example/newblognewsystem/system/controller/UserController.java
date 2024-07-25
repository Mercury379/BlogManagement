package com.example.newblognewsystem.system.controller;

import com.example.newblognewsystem.system.service.IUserService;
import com.newland.blog.entities.Advert;
import com.newland.blog.entities.User;
import com.newland.blog.util.aliyun.AliyunUtil;
import com.newland.blog.util.base.Result;
import com.newland.blog.util.enums.ArticleStatusEnum;
import com.newland.blog.util.enums.PlatformEnum;
import com.newland.blog.util.properties.AliyunProperties;
import com.newland.blog.util.properties.BlogProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(value = "用户管理接口", description = "用户管理接口, 提供用户信息的增删改查")
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    /**1. 登录验证用户密码(需解密)
     * 1. 新增用户(密码需用加密算法,头像需上传至OSS)
     * 2. 为用户赋予角色
     * 2. 根据用户ID查询其对应的所有菜单列表详细信息
     * 3. 根据用户ID返回其角色的详细信息
     * 4. 删除用户(假删除）
     * 5. 根据ID，查询用户的详细信息
     * 6. 返回所有用户的列表
     * 7. 修改用户信息
     * 8. 根据用户ID查询用户所有评论的历史记录(需远程调用)
     * 9. 根据用户ID查询用户发表的所有文章(需远程调用)
     * 10. 根据用户ID查询用户提出的所有问题(需远程调用)
     * 11. 统计用户近6个月发表的文章数(需远程调用)
     * 12. 统计用户近6个月发表的问题数(需远程调用)
     * 13. 统计用户近6个月发表的评论数(需远程调用)
     */

    //4. 根据用户ID查询其对应的所有菜单列表详细信息


    //5. 根据用户ID返回其角色的详细信息
    @Autowired
    private IUserService userService;

    @Autowired
    private BlogProperties blogProperties;

    @ApiOperation("登录验证用户密码(需解密)")
    @PostMapping
    public Result login() {
        return null;
    }

    @ApiOperation("新增用户(密码需用加密算法,头像需上传至OSS)")
    @PostMapping("/add")
    public Result add(@Validated User user, @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            // 将文件保存到OSS服务器
            AliyunProperties aliyun = blogProperties.getAliyun();
            Result fileRes = AliyunUtil.uploadFileToOss(PlatformEnum.USER, file, aliyun);
            if(fileRes.getCode() != 20000) {
                return Result.error(fileRes.getMessage());
            }
            user.setImageUrl(fileRes.getData().toString());
        }
        userService.saveUser(user);

        return Result.ok();
    }

    @ApiImplicitParam(
            name = "id",
            value = "用户ID",
            required = true
    )
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

}
