package com.newland.blog.article.req;

import com.newland.blog.entities.Advert;
import com.newland.blog.entities.Article;
import com.newland.blog.util.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 广告请求类
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "AdvertREQ对象", description = "广告查询条件")
public class AdvertREQ extends BaseRequest<Advert>{

    @ApiModelProperty(value = "广告标题")
    private String title;

    @ApiModelProperty(value = "状态(1:正常，0:禁用)")
    private Integer status;

}
