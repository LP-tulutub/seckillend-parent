package com.seckillend.mq;

import cn.hutool.json.JSONUtil;
import com.seckillend.pojo.SendMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component
@RocketMQMessageListener(topic = "${seckill.topic.channel}", consumerGroup = "${seckill.consumer.group}")
public class SeckillCommodityListener implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener {

    @Autowired
    private SeckillCommodityOperate seckillCommodityOperate;

    @Override
    public void onMessage(MessageExt mex) {
        // 不会使用的方法
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {

        defaultMQPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt messageExt : list) {
                    SendMessage msg = JSONUtil.toBean(new String(messageExt.getBody()), SendMessage.class);
                    if (msg == null) return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    log.warn("接受到了消息: " + msg);
                    int operation = seckillCommodityOperate.operation(msg);

                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; // 完成消费
            }
        });

    }
}
