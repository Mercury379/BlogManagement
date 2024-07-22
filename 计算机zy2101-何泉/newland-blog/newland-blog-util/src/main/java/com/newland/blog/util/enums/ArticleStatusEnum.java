package com.newland.blog.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 文章状态枚举类
 *
 */
@Getter
@AllArgsConstructor
public enum ArticleStatusEnum {

    DELETE(0, "已删除"), WAIT(1, "待审核"),
    SUCCESS(2, "审核通过"), FAIL(3, "审核不通过");

    private Integer code;
    private String desc;
}
