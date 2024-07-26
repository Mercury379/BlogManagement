package com.example.newblognewsystem.system.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.newblognewsystem.system.req.ArticleUserREQ;
import com.example.newblognewsystem.system.req.QuestionUserREQ;
import com.example.newblognewsystem.system.req.ReplayUserREQ;
import com.example.newblognewsystem.system.service.IUserService;
import com.newland.blog.entities.Article;
import com.newland.blog.entities.Replay;
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
     * 10. 根据用户ID查询用户所有回复的历史记录(需远程调用)
     * 11. 根据用户ID查询用户发表的所有文章(需远程调用)
     * 12. 根据用户ID查询用户提出的所有问题(需远程调用)
     * 13. 统计用户近6个月发表的文章数(需远程调用)
     * 14. 统计用户近6个月发表的问题数(需远程调用)
     * 15. 统计用户近6个月发表的评论数(需远程调用)
     */

    @Autowired
    private IUserService userService;
    @Autowired
    private BlogProperties blogProperties;

    @ApiOperation("登录验证用户密码(需解密)")
    @PostMapping
    public Result login(@RequestParam String userName, @RequestParam String password) {
        return userService.login(userName, password);
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

    //3. 为用户赋予角色
    @ApiImplicitParam(
            name = "id",
            value = "用户ID",
            required = true
    )
    @ApiOperation("为用户赋予角色")
    @GetMapping("/{id}/{role}")
    public Result assignRoles(@PathVariable("id") String id,
                              @PathVariable("role") String role){
        return userService.assignRoles(id,role);
    }

    //3-2.修改指定用户的角色
    @ApiOperation("修改指定用户的角色")
    @PutMapping("/updateRole/{id}/{role}")
    public Result updateRole(@PathVariable("id") String id,
                             @PathVariable("role") String role) {
        return userService.updateRole(id,role);
    }


    //4. 根据用户ID查询其对应的所有菜单列表详细信息
    @ApiOperation("根据用户ID返回菜单")
    @GetMapping({"/menu/{id}"})
    public Result findMenuByUserID(@PathVariable("id") String id) {
        return userService.findMenuByUserID(id);
    }

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
    @ApiOperation("修改用户信息接口")
    @PutMapping
    public Result update(@RequestBody User user) {
        return Result.ok(userService.updateById(user));
    }
    //10. 根据用户ID查询公开或未公开的文章历史记录接口
    @ApiOperation("根据用户ID查询公开或未公开的文章历史记录接口")
    @PostMapping("/article") // /article/user
    public Result findArticleListByUserId(@RequestBody ArticleUserREQ req) {
        return userService.findArticleListByUserId(req);
    }
    //11. 根据用户ID查询其回复的历史记录接口
    @ApiOperation("根据用户ID查询其回复的历史记录接口")
    @PostMapping("/replay") // /article/user
    public Result findReplayListByUserId(@RequestBody ReplayUserREQ req) {
        return userService.findReplayListByUserId(req);
    }
    //12.根据用户ID查询其发布的问题的历史记录接口
    @ApiOperation("根据用户ID查询其发布的问题的历史记录接口")
    @PostMapping("/question") // /article/user
    public Result findQuestionListByUserId(@RequestBody QuestionUserREQ req) {
        return userService.findQuestionListByUserId(req);
    }
    //13. 根据用户ID统计该用户近6个月公开或未公开的文章数
    @ApiImplicitParam(name = "id", value = "用户ID", required = true)
    @ApiOperation("根据用户ID统计该用户近6个月公开或未公开的文章数")
    @GetMapping("/article/{id}")
    public Result userMonthArticleTotal(@PathVariable("id") String id){
        return userService.userMonthArticleTotal(id);
    }
    //14.根据用户ID统计该用户近6个月发布的问题数
    @ApiImplicitParam(name = "id", value = "用户ID", required = true)
    @ApiOperation("根据用户ID统计该用户近6个月发布的问题数")
    @GetMapping("/question/{id}")
    public Result userMonthQuestionTotal(@PathVariable("id") String id){
        return userService.userMonthQuestionTotal(id);
    }
    //15. 根据用户ID统计该用户近6个月发布的回复数
    @ApiImplicitParam(name = "id", value = "用户ID", required = true)
    @ApiOperation("根据用户ID统计该用户近6个月发布的回复数")
    @GetMapping("/replay/{id}")
    public Result userMonthReplayTotal(@PathVariable("id") String id){
        return userService.userMonthReplayTotal(id);
    }
}
