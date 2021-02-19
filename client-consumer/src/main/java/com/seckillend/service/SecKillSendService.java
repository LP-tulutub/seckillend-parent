package com.seckillend.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SecKillSendService {

    @Value("${seckill.topic.channel}")
    private String seckillDestination;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void sendMessage(Message message){
        this.rocketMQTemplate.asyncSend(seckillDestination, message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.warn("asyncSend onSuccess: " + sendResult.getSendStatus());
            }

            @Override
            public void onException(Throwable throwable) {
                log.error("asyncSend onException: " + throwable.getMessage());
            }
        });
    }
}
