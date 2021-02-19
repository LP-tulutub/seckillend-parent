package com.seckillend.feign;

import com.seckillend.feign.fallback.GoodsFeignFallback;
import com.seckillend.pojo.SecKillGoods;
import com.seckillend.result.ResultLP;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "goods-service", fallback = GoodsFeignFallback.class)
public interface GoodsFeign {

    @GetMapping("/goods/goodslist/{pagination}/{size}")
    public ResultLP<List<SecKillGoods>> goodsList(@PathVariable Integer pagination, @PathVariable Integer size);

    @GetMapping("/goods/goodsdetail/{id}")
    public ResultLP<SecKillGoods> goodsDetail(@PathVariable Integer id);

}
