package com.example.newblognewsystem.system.client;

import com.newland.blog.util.base.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@Component
@FeignClient(value = "question-server2",url="127.0.0.1:8002")
public interface QuestionClient {
    @GetMapping("/question/question/usermonth/{id}")
    Result userMonthQuestionTotal(@PathVariable("id") String id);
    @GetMapping("/question/replay/usermonth/{id}")
    public Result userMonthReplayTotal(@PathVariable("id") String id);
}
