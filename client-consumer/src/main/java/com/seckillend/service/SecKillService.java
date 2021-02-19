package com.seckillend.service;

import cn.hutool.core.util.IdUtil;
import com.seckillend.enums.RedisTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class SecKillService {

    @Value("${redis.key.seckillId}")
    private String redisUGId;
    @Value("${redis.key.goodsRepertory}")
    private String goodsRepertoryKey;
    @Value("${redis.key.hidePath}")
    private String hidePath;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public boolean checkSecKill(String secKillId){
        Object obj = this.redisTemplate.opsForValue().get(redisUGId + secKillId);
        if (obj==null || obj.equals("")){
            return false;
        }
        return true;
    }

    public boolean checkRepertory(long goodsId){
        int obj = (int)this.redisTemplate.opsForValue().get(goodsRepertoryKey + goodsId);
        if (obj==0 || obj<0){
            return false;
        }
        return true;
    }

    public String createHidePath(){
        try {
            String pathId = this.hidePath + IdUtil.randomUUID() + System.currentTimeMillis();
            this.redisTemplate.opsForValue().set(pathId, 1, (int) RedisTime.KILL_VERIFICATION_TIME, TimeUnit.SECONDS);
            return pathId;
        }catch (Exception e){
            log.error(e.getMessage() + Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    public boolean checkPath(String path){
        Object obj = this.redisTemplate.opsForValue().get(path);
        if (obj==null || obj.equals("")){
            return false;
        }
        return true;
    }

}
