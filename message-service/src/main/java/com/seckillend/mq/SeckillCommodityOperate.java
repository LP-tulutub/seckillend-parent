package com.seckillend.mq;


import com.seckillend.enums.RedisTime;
import com.seckillend.pojo.SendMessage;
import com.seckillend.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class SeckillCommodityOperate {

    @Value("${redis.key.seckillId}")
    private String redisUGId;
    @Value("${redis.key.goodsRepertory}")
    private String goodsRepertoryKey;
    @Value("${redis.key.goodsLock}")
    private String goodsLockKey;
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private MessageService messageServiceImpl;

    //@GlobalTransactional(name = "SeckillCommodityOperate-operation", timeoutMills = 30000, rollbackFor = Exception.class)
    public int operation(SendMessage msg){

        final String secKillId = String.valueOf(msg.getUserId()) + msg.getGoodsId();
        //创建公平锁
        RLock lock = redissonClient.getFairLock(goodsLockKey + msg.getGoodsId());
        //加锁
        lock.lock();
        try {
            //检查是否已经秒杀过了
            Object obj = this.redisTemplate.opsForValue().get(redisUGId + secKillId);
            if (obj != null && (int)obj == 1){
                return 0;
            }
            //查看还有没有商品
            int repertory = (int) redisTemplate.opsForValue().get(goodsRepertoryKey + msg.getGoodsId());
            if (repertory>0){
                //有的话Redis商品库存减1
                redisTemplate.opsForValue().decrement(goodsRepertoryKey + msg.getGoodsId());
            }else {
                //没有商品则给用户返回秒杀失败信息
                messageServiceImpl.failedSecKill(msg, secKillId);
                return 0;
            }
            //if (true) throw new RuntimeException("GlobalTransactional 全局事务测试");
            //有商品则开始提交订单，订单提交失败不用再把redis库存加回去，对这个活动影响不大
            int sql = messageServiceImpl.successSecKill(msg, secKillId);
            if (sql==4){
                //订单提交成功，防止用户多次秒杀
                redisTemplate.opsForValue().set(redisUGId + secKillId, 1, RedisTime.USER_SEC_TIME, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            log.error(e.getMessage() + Arrays.toString(e.getStackTrace()));
        } finally {
            //解锁
            lock.unlock();
        }

        return 1;
    }
}
