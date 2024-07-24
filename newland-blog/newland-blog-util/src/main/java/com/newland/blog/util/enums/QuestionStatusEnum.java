package com.newland.blog.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 问题状态枚举类
 *
 */
@Getter
@AllArgsConstructor
public enum QuestionStatusEnum {
    DELETE(0, "已删除"),
    UNSOLVED(1, "未解决"),
    SOLVE(2, "已解决");

    private Integer code;
    private String desc;
}
