package com.newland.blog.article.service;

import com.newland.blog.article.req.ArticleListREQ;
import com.newland.blog.article.req.ArticleREQ;
import com.newland.blog.article.req.ArticleUserREQ;
import com.newland.blog.entities.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.blog.util.base.Result;
import com.newland.blog.util.enums.ArticleStatusEnum;

/**
 * <p>
 * 文章信息表 服务类
 * </p>
 */
public interface IArticleService extends IService<Article> {

    /**
     * 1 条件分页查询文章列表
     * @param req
     * @return
     */
    Result queryPage(ArticleREQ req);

    /**
     *2 通过文章id查询文章详情及标签
     * @param id 文章id
     * @return
     */
    Result findArticleAndLabelById(String id);

    /**
     * 3 4 修改或新增文章
     * @param article
     * @return
     */
    Result updateOrSave(Article article);

    /**
     *5 6 7文章删除 —— 修改状态：
     * @param id 文章id
     * @param statusEnum 状态枚举类
     * @return
     */
    Result updateStatus(String id, ArticleStatusEnum statusEnum);

    /**
     *8  根据用户ID查询公开或未公开的文章列表
     * @param req
     * @return
     */
    Result findListByUserId(ArticleUserREQ req);

    /**
     *9 根据文章id更新点赞数
     * @param id 文章id
     * @param count 点赞接收+1，取消点赞 -1
     * @return
     */
    Result updateThumhup(String id, int count);

    /**
     *10 统计公开 且 审核通过的文章数
     * @return
     */
    Result getArticleTotal();

    /**
     * 11 统计每个分类下的文章数（调用视图）
     * @return
     */
    Result selectCategoryTotal();


    /**
     * 12 查询最近6个月的文章数量(公开 审核通过)
     */
    Result SelectMonthTotal();








}
