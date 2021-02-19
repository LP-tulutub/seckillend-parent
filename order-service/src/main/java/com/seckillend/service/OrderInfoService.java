package com.seckillend.service;

import com.seckillend.entity.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.seckillend.pojo.SeckillOrderGoods;
import com.seckillend.pojo.SendMessage;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author root
 * @since 2020-08-21
 */
public interface OrderInfoService extends IService<OrderInfo> {

    List<SeckillOrderGoods> oneToOneByUserId(Long id);
}
