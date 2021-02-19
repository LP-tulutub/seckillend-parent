package com.seckillend.feign;

import com.seckillend.pojo.SendMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "order-service")
public interface OrderFeign {

    @PostMapping("/order/insert/msg")
    Integer insertMsg(@RequestBody SendMessage msg);
}
