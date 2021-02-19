package com.seckillend.service;

import com.seckillend.pojo.SendMessage;

public interface OrderService {

    Integer insOrderBySendMessage(SendMessage msg);
}
