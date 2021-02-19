package com.seckillend.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.seckillend.entity.MiaoshaMessage;
import com.seckillend.entity.MiaoshaMessageUser;
import com.seckillend.feign.OrderFeign;
import com.seckillend.mapper.MiaoshaMessageMapper;
import com.seckillend.mapper.MiaoshaMessageUserMapper;
import com.seckillend.pojo.SendMessage;
import com.seckillend.service.MessageService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service

public class MessageServiceImpl implements MessageService {

    private static Snowflake snowflakeMessageId = IdUtil.getSnowflake(1, 1);

    @Resource
    private MiaoshaMessageMapper miaoshaMessageMapper;
    @Resource
    private MiaoshaMessageUserMapper miaoshaMessageUserMapper;
    @Resource
    private OrderFeign orderFeign;

    @Override
    public Integer failedSecKill(SendMessage msg, String secKillId) {
        long messageId = snowflakeMessageId.nextId();
        //插入秒杀信息表
        MiaoshaMessage miaoshaMessage = new MiaoshaMessage();
        miaoshaMessage.setId(messageId);
        miaoshaMessage.setGoodName(msg.getGoodsName());
        miaoshaMessage.setPrice(msg.getMiaoshaPrice());
        miaoshaMessage.setContent("没有商品秒杀失败");
        miaoshaMessage.setStatus(1);
        miaoshaMessage.setMessageType(0);
        miaoshaMessage.setSendType(1);
        int insMiaoshaMessage = this.miaoshaMessageMapper.insert(miaoshaMessage);
        //插入秒杀用户表
        MiaoshaMessageUser miaoshaMessageUser = new MiaoshaMessageUser();
        miaoshaMessageUser.setId(messageId);
        miaoshaMessageUser.setMessageId(messageId);
        miaoshaMessageUser.setUserId(msg.getUserId());
        miaoshaMessageUser.setGoodsId(msg.getGoodsId());
        int insMiaoshaMessageUser = this.miaoshaMessageUserMapper.insert(miaoshaMessageUser);
        return (insMiaoshaMessage + insMiaoshaMessageUser);
    }

    @GlobalTransactional(name = "MessageServiceImpl-successSecKill", timeoutMills = 60000, rollbackFor = Exception.class)
    @Override
    public Integer successSecKill(SendMessage msg, String secKillId) {
        long messageId = snowflakeMessageId.nextId();
        //插入秒杀信息表
        MiaoshaMessage miaoshaMessage = new MiaoshaMessage();
        miaoshaMessage.setId(messageId);
        miaoshaMessage.setGoodName(msg.getGoodsName());
        miaoshaMessage.setPrice(msg.getMiaoshaPrice());
        miaoshaMessage.setContent("已成功秒杀");
        miaoshaMessage.setStatus(1);
        miaoshaMessage.setMessageType(0);
        miaoshaMessage.setSendType(1);
        int insMiaoshaMessage = this.miaoshaMessageMapper.insert(miaoshaMessage);
        // 测试分布式事务
        //if (true) throw new RuntimeException("GlobalTransactional test");
        //插入秒杀用户表
        MiaoshaMessageUser miaoshaMessageUser = new MiaoshaMessageUser();
        miaoshaMessageUser.setId(messageId);
        miaoshaMessageUser.setMessageId(messageId);
        miaoshaMessageUser.setUserId(msg.getUserId());
        miaoshaMessageUser.setGoodsId(msg.getGoodsId());
        int insMiaoshaMessageUser = this.miaoshaMessageUserMapper.insert(miaoshaMessageUser);
        //插入订单信息表 + 插入秒杀订单表
        Integer insOrder = this.orderFeign.insertMsg(msg);
        // 测试分布式事务
        //if (true) throw new RuntimeException("GlobalTransactional test");
        return (insMiaoshaMessage + insMiaoshaMessageUser + insOrder);
    }
}
