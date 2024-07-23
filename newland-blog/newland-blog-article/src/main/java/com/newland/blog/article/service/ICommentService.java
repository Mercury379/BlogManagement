package com.newland.blog.article.service;

import com.newland.blog.entities.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.blog.util.base.Result;

/**
 * <p>
 * 评论信息表 服务类
 * </p>
 */
public interface ICommentService extends IService<Comment> {

    /**
     * 通过文章id递归查询所有评论
     * @param articleId 文章id
     * @return
     */
    Result findByArticleId(String articleId);

    /**
     * 通过评论id递归删除评论信息
     * @param id 评论id
     * @return
     */
    Result deleteById(String id);
}
