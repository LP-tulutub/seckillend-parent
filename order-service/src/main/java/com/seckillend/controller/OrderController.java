package com.seckillend.controller;

import com.seckillend.pojo.SendMessage;
import com.seckillend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("//order")
public class OrderController {

    @Autowired
    private OrderService orderServiceImpl;

    @PostMapping("/insert/msg")
    public Integer insertMsg(@RequestBody SendMessage msg){
        return this.orderServiceImpl.insOrderBySendMessage(msg);
    }

}
