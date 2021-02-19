package com.seckillend.feign;

import com.seckillend.feign.fallback.OrderFeignFallback;
import com.seckillend.pojo.SeckillOrderGoods;
import com.seckillend.result.ResultLP;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "order-service", fallback = OrderFeignFallback.class)
public interface OrderFeign {

    @GetMapping("/order-info/list/{id}")
    public ResultLP<List<SeckillOrderGoods>> list(@PathVariable Long id);


}
