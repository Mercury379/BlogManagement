package com.example.newblognewsystem.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newland.blog.entities.Article;
import com.newland.blog.entities.Role;
import com.newland.blog.entities.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    Role findRolebyID(String id);

}
