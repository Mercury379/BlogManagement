package com.example.newblognewsystem.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newland.blog.entities.Article;
import com.newland.blog.entities.Menu;
import com.newland.blog.entities.Role;
import com.newland.blog.entities.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    Role findRoleByID(String id);

    List<Menu> findMenuByUserID(String id);

}
