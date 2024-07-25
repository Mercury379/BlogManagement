package com.newland.blog.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_menu")
@ApiModel(value="Menu对象", description="菜单表")
public class Menu {
    private String id;
    private String parentId;
    private String name;
    private String url;
    private int type; // 1目录，2菜单，3按钮
    private String code;
    private String icon;
    private int sort;
    private String remark;
    private Timestamp createDate;
    private Timestamp updateDate;

}
