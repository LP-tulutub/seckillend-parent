package com.seckillend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
public class UserService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public LinkedHashMap getUser(String token){
        return (LinkedHashMap) this.redisTemplate.opsForValue().get(token);
    }


}
