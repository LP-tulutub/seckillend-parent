package com.seckillend.controller;

import com.seckillend.pojo.SeckillOrderGoods;
import com.seckillend.result.ResultLP;
import com.seckillend.service.OrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("//order-info")
@Slf4j
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoServiceImpl;

    @GetMapping("/list/{id}")
    public ResultLP<List<SeckillOrderGoods>> list(@PathVariable Long id){
        ResultLP<List<SeckillOrderGoods>> resultLP = ResultLP.build();
        resultLP.setData(this.orderInfoServiceImpl.oneToOneByUserId(id));
        log.warn(resultLP.toString());
        return resultLP;
    }



}
