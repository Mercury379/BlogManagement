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
@ApiModel(value = "QuestionReplayREQ对象", description = "问题回复的查询条件")
public class QuestionReplayREQ extends BaseRequest<Replay> {
    @ApiModelProperty(value = "问题ID")
    private String questionId;
}
