package com.seckillend.enums;

public class RedisTime {
    // 验证图过期时间
    public final static long VERIFICATION_TIME = 60*5;

    // 登陆cookies过期时间
    public final static long LOGIN_TIME = 60*60*2;

    // 秒杀验证图过期时间
    public final static long KILL_VERIFICATION_TIME = 60*1;

    // 防止用户多次秒杀更新时间
    public final static long USER_SEC_TIME = 60*60*2;
}
