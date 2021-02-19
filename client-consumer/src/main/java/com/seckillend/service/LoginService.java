package com.seckillend.service;


import cn.hutool.core.util.IdUtil;
import com.seckillend.enums.RedisTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class LoginService {

    @Value("${redis.key.login}")
    private String loginKey;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public String setLoginInRedis(Object obj){
        String cookiesId = loginKey + IdUtil.randomUUID();
        this.redisTemplate.opsForValue().set(cookiesId, obj, RedisTime.LOGIN_TIME, TimeUnit.SECONDS);
        return cookiesId;
    }

    public int getLoginInRedis(String cookiesId){
        Object obj = this.redisTemplate.opsForValue().get(cookiesId);
        if (obj==null || obj.equals("")){
            return 0;
        }
        return 1;
    }
}
