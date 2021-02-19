package com.seckillend.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seckillend.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seckillend.pojo.SecKillGoods;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author root
 * @since 2020-08-13
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    @Select("select A.id,A.goods_name,A.goods_img,A.goods_price,B.miaosha_price,B.stock_count from goods A left join miaosha_goods B on A.id=B.id")
    List<SecKillGoods> uniteMiaoshaGoodsSelByPage(Page page, Wrapper wrapper);

    @Select("select A.id,A.goods_name,A.goods_img,A.goods_price,B.miaosha_price,B.stock_count,B.start_date,B.end_date from goods A left join miaosha_goods B on A.id=B.id where A.id=#{id}")
    SecKillGoods uniteMiaoshaGoodsSelById(Integer id);

}
