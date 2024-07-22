package com.newland.blog.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdvertStatusEnum {
    DISNABLE(0, "禁用"), ENABLE(1, "正常");

    private Integer code;
    private String desc;
}
