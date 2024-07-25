package com.newland.blog.question.req;

import com.newland.blog.entities.Question;
import com.newland.blog.util.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value = "QuestionREQ对象", description = "问题的模糊查询")
public class QuestionREQ  extends BaseRequest<Question> {
}
