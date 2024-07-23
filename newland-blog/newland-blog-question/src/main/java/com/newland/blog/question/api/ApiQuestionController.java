package com.newland.blog.question.api;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "问答管理接口", description = "问答管理接口,不用通过身份认证即可调用接口")
@RestController
@RequestMapping("/api/question")
public class ApiQuestionController {







}
