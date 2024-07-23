package com.newland.blog.article.service;

import com.newland.blog.article.req.AdvertREQ;
import com.newland.blog.entities.Advert;
import com.baomidou.mybatisplus.extension.service.IService;
import com.newland.blog.util.base.Result;

/**
 * <p>
 * 广告信息表 服务类
 * </p>
 */
public interface IAdvertService extends IService<Advert> {

    /**
     * 条件分页查询广告列表
     * @param req
     * @return
     */
    Result queryPage(AdvertREQ req);

    /**
     * 删除广告及图片
     * @param id 广告id
     * @return
     */
    Result deleteById(String id);

    /**
     * 查询指定广告查询的所有广告信息（状态为正常的）
     * @param position
     * @return
     */
    Result findByPosition(int position);
}
