package com.newland.blog.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.newland.blog.article.req.ArticleListREQ;
import com.newland.blog.article.req.ArticleREQ;
import com.newland.blog.article.req.ArticleUserREQ;
import com.newland.blog.entities.Article;
import com.newland.blog.article.mapper.ArticleMapper;
import com.newland.blog.article.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.blog.util.base.Result;
import com.newland.blog.util.enums.ArticleStatusEnum;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 文章信息表 服务实现类
 * </p>
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Override
    public Result queryPage(ArticleREQ req) {
        QueryWrapper<Article> wrapper = new QueryWrapper();
        // 标题
        if(StringUtils.isNotEmpty(req.getTitle())) {
            wrapper.like("title", req.getTitle());
        }
        // 状态
        if(req.getStatus() != null) {
            wrapper.eq("status", req.getStatus());
        }
        wrapper.orderByDesc("update_date");
        IPage<Article> page = baseMapper.selectPage(req.getPage(), wrapper);
        return Result.ok(page);
    }

    @Override
    public Result findArticleAndLabelById(String id) {
        return Result.ok( baseMapper.findArticleAndLabelById(id) );
    }

    @Transactional // 事务管理
    @Override
    public Result updateOrSave(Article article) {
        // 1. 如果id不为空，则是更新操作
        if(StringUtils.isNotEmpty(article.getId())) {
            // 更新：先删除文章中间表数据，再新增到中间表
            baseMapper.deleteArticleLabel(article.getId());
            // 更新：将更新时间设置当前时间
            article.setUpdateDate(new Date());
        }

        // 如果文章是ispublic不公开的，直接审核通过(status)，否则等待审核
        if(article.getIspublic() == 0) { // 0：不公开，1：公开
            article.setStatus(ArticleStatusEnum.SUCCESS.getCode()); // 0: 已删除, 1:未审核，2:审核通过，3：审核未通过
        }else {
            article.setStatus(ArticleStatusEnum.WAIT.getCode());
        }

        // 更新或保存文章信息（新增数据后，会将这条新增数据的主键id值放到id属性中）
        super.saveOrUpdate(article);

        // 新增标签数据到文章标签中间表中
        if(CollectionUtils.isNotEmpty(article.getLabelIds())) {
            baseMapper.saveArticleLabel(article.getId(), article.getLabelIds());
        }

        // 返回文章id
        return Result.ok(article.getId());
    }

    @Override
    public Result updateStatus(String id, ArticleStatusEnum statusEnum) {
        // 先查询当前数据库的数据
        Article article = baseMapper.selectById(id);
        // 将状态值 更新
        article.setStatus(statusEnum.getCode());
        article.setUpdateDate(new Date());
        baseMapper.updateById(article);
        return Result.ok();
    }

    @Override
    public Result findListByUserId(ArticleUserREQ req) {
        if(StringUtils.isEmpty(req.getUserId())) {
            return Result.error("无效用户信息");
        }

        QueryWrapper<Article> wrapper = new QueryWrapper();
        wrapper.eq("user_id", req.getUserId());

        if(req.getIsPublic() != null) {
            // ispublic 字段名都是小写
            wrapper.eq("ispublic", req.getIsPublic());
        }

        // 排序
        wrapper.orderByDesc("update_date");

        IPage<Article> page = baseMapper.selectPage(req.getPage(), wrapper);

        return Result.ok(page);
    }

    @Override
    public Result updateThumhup(String id, int count) {
        if(count != -1 && count != 1) {
            return Result.error("无效操作");
        }

        if(StringUtils.isEmpty(id)) {
            return Result.error("无效操作");
        }

        // 查询这篇文章现有数据，查询到后，将点赞数进行更新
        Article article = baseMapper.selectById(id);
        if(article == null) {
            return Result.error("文章不存在");
        }

        if(article.getThumhup() <= 0 && count == -1) {
            return Result.error("无效操作");
        }
        // 更新点赞数
        article.setThumhup( article.getThumhup() + count );
        baseMapper.updateById(article);

        return Result.ok();
    }

    @Override
    public Result updateViewCount(String id) {
        if(StringUtils.isEmpty(id)) {
            return Result.error("无效操作");
        }
        Article article = baseMapper.selectById(id);
        if(article == null){
            return Result.error("文章不存在");
        }
        article.setViewCount( article.getViewCount() + 1 );

        baseMapper.updateById(article);
        return Result.ok();
    }

    @Override
    public Result findListByLabelIdOrCategoryId(ArticleListREQ req) {
        IPage<Article> page = baseMapper.findListByLabelIdOrCategoryId(req.getPage(), req);
        return Result.ok(page);
    }

    @Override
    public Result getArticleTotal() {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        // 状态是审核通过
        wrapper.eq("status", ArticleStatusEnum.SUCCESS.getCode());
        // 公开
        wrapper.eq("ispublic", 1);

        Integer total = baseMapper.selectCount(wrapper);
        return Result.ok(total);
    }

    @Override
    public Result selectCategoryTotal() {
        List<Map<String, Object>> maps = baseMapper.selectCategoryTotal();

        // 将分类名称单独提取到集合中
        List<Object> nameList = new ArrayList<>();
        for(Map<String, Object> map: maps) {
            nameList.add( map.get("name") );
        }

        // 封装响应数据
        Map<String, Object> data = new HashMap<>();
        data.put("nameAndValueList", maps);
        data.put("nameList", nameList);

        return Result.ok(data);
    }

    @Override
    public Result selectMonthArticleTotal() {
        List<Map<String, Object>> maps = baseMapper.selectMonthArticleTotal();

        // 将年月提取到集合中
        List<Object> yearMonthList = new ArrayList<>();
        // 将每个月的文章数提取到集合中
        List<Object> articleTotalList = new ArrayList<>();

        for(Map<String, Object> map: maps) {
            yearMonthList.add ( map.get("year_month") );
            articleTotalList.add( map.get("total") );
        }

        // 封装响应的data数据
        Map<String, Object> data = new HashMap<>();
        data.put("yearMonthList", yearMonthList);
//        注意，当前前端要的key就是 aritcleTotalList ，即使key单词有问题也就将错就错
        data.put("aritcleTotalList", articleTotalList);

        return Result.ok(data);
    }
}
