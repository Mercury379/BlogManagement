package com.example.newblognewsystem.system.client;

import com.newland.blog.util.base.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@Component
@FeignClient(value = "article-server",url="127.0.0.1:8001")
public interface ArticleClient {
    @GetMapping("/article/article/usermonth/{id}")
    Result userMonthArticleTotal(@PathVariable("id") String id);
}
