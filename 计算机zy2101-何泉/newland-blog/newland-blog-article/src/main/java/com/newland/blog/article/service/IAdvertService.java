package com.newland.blog.article.service;

import com.newland.blog.article.req.AdvertREQ;
import com.newland.blog.article.req.ArticleREQ;
import com.newland.blog.entities.Advert;
import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.blog.util.base.Result;
import com.newland.blog.util.enums.AdvertStatusEnum;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 广告信息表 服务类
 * </p>
 */
public interface IAdvertService extends IService<Advert> {
    /**
     * 1 条件分页查询广告列表
     * @param req
     * @return
     */
    Result queryPage(AdvertREQ req);

    /**
     * 2 根据id修改广告的状态
     * @param id
     * @param statusEnum
     * @return
     */
    Result updateStatus(String id, AdvertStatusEnum statusEnum);
    /**
     * 3 根据id查询广告详情
     * @param id
     * @return
     */
    Result findAdvertById(String id);
    /**
     * 4 修改或新增广告
     * @param advert
     * @return
     */
    Result updateOrSave(Advert advert);

    /**
     * 5 从数据库中真删除广告
     * @param id
     * @return
     */
    Result delete(String id);



}
