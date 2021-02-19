package com.seckillend.controller;

import com.seckillend.feign.OrderFeign;
import com.seckillend.pojo.SeckillOrderGoods;
import com.seckillend.result.ResultLP;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;


@Controller
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderFeign orderFeign;

    @GetMapping("/list/{id}")
    public String detail(Model model, @PathVariable Long id){
        ResultLP<List<SeckillOrderGoods>> resultLP = this.orderFeign.list(id);
        List<SeckillOrderGoods> list = resultLP.getData();
        model.addAttribute("list", list);
        return "order_list";
    }


}
