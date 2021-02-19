package com.seckillend.mapper;

import com.seckillend.entity.OrderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seckillend.pojo.SecKillGoods;
import com.seckillend.pojo.SeckillOrderGoods;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author root
 * @since 2020-08-21
 */
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

    @Select("select goods_name,goods_img,goods_price from goods where id=#{id}")
    SecKillGoods selGoods(Long id);

    @Results(value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "goodsId", column = "goods_id"),
            @Result(property = "goods", column = "goods_id", one = @One(select = "com.seckillend.mapper.OrderInfoMapper.selGoods"))
    })
    @Select("select user_id,goods_id,goods_name,status,create_time from order_info where user_id=#{id}")
    List<SeckillOrderGoods> oneToOneByUserId(Long id);


}
