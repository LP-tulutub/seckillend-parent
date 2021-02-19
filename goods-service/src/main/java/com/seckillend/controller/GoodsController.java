package com.seckillend.controller;

import com.seckillend.pojo.SecKillGoods;
import com.seckillend.result.ResultLP;
import com.seckillend.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Autowired
    private GoodsService goodsServiceImpl;

    @GetMapping("/goodslist/{pagination}/{size}")
    public ResultLP<List<SecKillGoods>> goodsList(@PathVariable Integer pagination, @PathVariable Integer size){
        return this.goodsServiceImpl.selSecKillGoodsByPage(pagination, size);
    }

    @GetMapping("/goodsdetail/{id}")
    public ResultLP<SecKillGoods> goodsDetail(@PathVariable Integer id){
        return this.goodsServiceImpl.selGoodsSecKillById(id);
    }

}
