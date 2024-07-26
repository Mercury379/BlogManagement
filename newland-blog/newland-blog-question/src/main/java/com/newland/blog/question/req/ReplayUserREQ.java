package com.newland.blog.question.req;

import com.newland.blog.entities.Question;
import com.newland.blog.entities.Replay;
import com.newland.blog.util.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value = "ReplayUserREQ对象", description = "用户ID的封装")
public class ReplayUserREQ extends BaseRequest<Replay> {
    @ApiModelProperty(value = "用户ID")
    private String userId;
}
