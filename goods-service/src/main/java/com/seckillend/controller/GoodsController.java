package com.seckillend.controller;

import com.seckillend.entity.Goods;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author root
 * @since 2020-08-13
 */
@RestController
@RequestMapping("//goods")
public class GoodsController {


    @GetMapping("/test")
    public Object test(){

        return new Goods().selectAll();
    }

    @GetMapping("/test2")
    public Object test2(){
        return new Goods().deleteById(3);
    }

}
