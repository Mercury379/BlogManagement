package com.newland.blog.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.newland.blog.article.mapper.ArticleMapper;
import com.newland.blog.article.req.AdvertREQ;
import com.newland.blog.article.req.ArticleREQ;
import com.newland.blog.article.service.IArticleService;
import com.newland.blog.entities.Advert;
import com.newland.blog.article.mapper.AdvertMapper;
import com.newland.blog.article.service.IAdvertService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newland.blog.entities.Article;
import com.newland.blog.util.aliyun.AliyunUtil;
import com.newland.blog.util.base.Result;
import com.newland.blog.util.enums.AdvertStatusEnum;
import com.newland.blog.util.enums.ArticleStatusEnum;
import com.newland.blog.util.properties.BlogProperties;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 * 广告信息表 服务实现类
 * </p>
 */
@Service
public class AdvertServiceImpl extends ServiceImpl<AdvertMapper, Advert> implements IAdvertService{

    //1 条件分页查询广告列表
    @Override
    public Result queryPage(AdvertREQ req) {
        QueryWrapper<Advert> wrapper = new QueryWrapper();
        //1  判断标题是否为空
        if (StringUtils.isNotEmpty(req.getTitle())) {
            //sql拼接
            wrapper.like("title", req.getTitle());
        }
        //2 判断状态是否为空
        if (req.getStatus() != null) {
            //sql拼接
            wrapper.eq("status", req.getStatus());
        }
        //3  将查询后的结果进行排序
        wrapper.orderByDesc("update_date");
        //4  将查询排序后的数据进行分页
        IPage<Advert> page = baseMapper.selectPage(req.getPage(), wrapper);
        //5 将分页后的数据返回
        return Result.ok(page);
    }

    //2 根据id修改广告的状态
    @Override
    public Result updateStatus(String id, AdvertStatusEnum statusEnum) {
        //1 获取要修改的广告内容
        Advert advert=baseMapper.selectById(id);
        //2 修改广告对象状态
        advert.setStatus(statusEnum.getCode());
        //3 更改广告的修改时间
        advert.setUpdateDate(new Date());
        //4 在数据库中修改该广告
        baseMapper.updateById(advert);
        //5 返回修改成功
        return Result.ok();
    }

    //3 根据id查询广告详情
    @Override
    public Result findAdvertById(String id) {
        //1 从数据库中根据id查询该广告记录
        Advert advert = baseMapper.selectById(id);
        //2 封装广告详情
        return Result.ok(advert);
    }

    //4 修改或新增广告
    @Override
    public Result updateOrSave(Advert advert) {
        //1 判断广告ID是否存在 如果ID不为空 更新操作如果为空 新增操作
        if (StringUtils.isNotEmpty(advert.getId())) {
            // 更新修改时间
            advert.setUpdateDate(new Date());
        }
        //2 如果广告本身为正常状态，修改后应为禁用状态（不确定是否是这样）
//        if(advert.getStatus()==AdvertStatusEnum.ENABLE.getCode()){
//            advert.setStatus(AdvertStatusEnum.DISNABLE.getCode());
//        }
        //2 更新或保存文章信息（新增数据后，会将这条新增数据的主键id值放到id属性中）
        super.saveOrUpdate(advert);
        //3 返回文章id
        return Result.ok(advert.getId());
    }

    //5 从数据库中根据id真删除广告记录
    @Override
    public Result delete(String id) {
        //1 根据id删除广告记录
        super.removeById(id);
        //2 返回删除成功
        return Result.ok();
    }


}
