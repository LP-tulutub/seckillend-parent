package com.seckillend.service;

import com.seckillend.pojo.SendMessage;

public interface MessageService {

    public Integer failedSecKill(SendMessage msg, String secKillId);

    public Integer successSecKill(SendMessage msg, String secKillId);
}
