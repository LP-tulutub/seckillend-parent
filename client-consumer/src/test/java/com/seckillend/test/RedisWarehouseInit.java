package com.seckillend.test;

import com.seckillend.AppClientConsumer;
import com.seckillend.controller.MessageController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppClientConsumer.class)
public class RedisWarehouseInit {

    @Value("${redis.key.goodsRepertory}")
    private String goodsRepertoryKey;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void initRedis(){
        this.redisTemplate.opsForValue().set(goodsRepertoryKey + 1, 9);
        this.redisTemplate.opsForValue().set(goodsRepertoryKey + 2, 9);
        this.redisTemplate.opsForValue().set(goodsRepertoryKey + 3, 9);
        this.redisTemplate.opsForValue().set(goodsRepertoryKey + 4, 9);
        this.redisTemplate.opsForValue().set(goodsRepertoryKey + 5, 5);
        MessageController.localOverMap.clear();
    }















}
