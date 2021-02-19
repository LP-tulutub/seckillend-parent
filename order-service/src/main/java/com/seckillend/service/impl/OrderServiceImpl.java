package com.seckillend.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.seckillend.entity.MiaoshaOrder;
import com.seckillend.entity.OrderInfo;
import com.seckillend.mapper.MiaoshaOrderMapper;
import com.seckillend.mapper.OrderInfoMapper;
import com.seckillend.pojo.SendMessage;
import com.seckillend.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements OrderService {

    private static Snowflake snowflakeOrderId = IdUtil.getSnowflake(2, 2);

    @Resource
    private OrderInfoMapper orderInfoMapper;
    @Resource
    private MiaoshaOrderMapper miaoshaOrderMapper;

    @Override
    public Integer insOrderBySendMessage(SendMessage msg) {
        long orderId = snowflakeOrderId.nextId();
        // 插入订单信息表
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setId(orderId);
        orderInfo.setUserId(msg.getUserId());
        orderInfo.setGoodsId(msg.getGoodsId());
        orderInfo.setGoodsName(msg.getGoodsName());
        orderInfo.setGoodsPrice(msg.getMiaoshaPrice());
        orderInfo.setGoodsCount(1);
        orderInfo.setStatus(0);
        int insOrderInfo = this.orderInfoMapper.insert(orderInfo);
        // 插入秒杀订单表
        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setId(orderId);
        miaoshaOrder.setOrderId(orderId);
        miaoshaOrder.setGoodsId(msg.getGoodsId());
        miaoshaOrder.setUserId(msg.getUserId());
        int insMiaoshaOrder = this.miaoshaOrderMapper.insert(miaoshaOrder);
        // 测试分布式事务
        //if (true) throw new RuntimeException("GlobalTransactional test");
        return (insOrderInfo + insMiaoshaOrder);
    }
}
