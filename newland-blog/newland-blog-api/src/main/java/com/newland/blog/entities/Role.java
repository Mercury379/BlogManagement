package com.newland.blog.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_role")
@ApiModel(value="role对象", description="角色信息表")
public class Role {
    // id varchar, name varchar, create_date timestamp,update_date timestamp
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "角色类型")
    private String name;

    @ApiModelProperty(value = "权限说明")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "更新时间")
    private Date updateDate;

}
