package com.newland.blog.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 评论信息表
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("mxg_comment")
@ApiModel(value="Comment对象", description="评论信息表")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "-1表示正常回复，其他值表示是评论的回复")
    private String parentId;

    @ApiModelProperty(value = "评论者用户id")
    private String userId;

    @ApiModelProperty(value = "评论者用户昵称")
    private String nickName;

    @ApiModelProperty(value = "评论者头像url")
    private String userImage;

    @ApiModelProperty(value = "文章id")
    private String articleId;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "子评论集合")
    @TableField(exist = false)
    private List<Comment> children;


}
