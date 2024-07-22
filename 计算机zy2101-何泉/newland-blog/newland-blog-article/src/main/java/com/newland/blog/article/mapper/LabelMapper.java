package com.newland.blog.article.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.newland.blog.article.req.LabelREQ;
import com.newland.blog.entities.Label;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 标签表 Mapper 接口
 * </p>
 */
public interface LabelMapper extends BaseMapper<Label> {

    /**
     * 如果自定义sql要分页查询，只要在mapper写不带分页功能的查询sql语句，mybatis-plus它会自动将sql进行分页
     * 第1个参数必须传入分页对象 Page, 第2个参数是你查询条件的参数, 取一个别名
     * 最终会将查询到的数据封装到 IPage实现类中
     * @return
     */
    IPage<Label> queryPage(IPage<Label> page, @Param("req") LabelREQ req);

}
