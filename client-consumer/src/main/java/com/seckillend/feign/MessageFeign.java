package com.seckillend.feign;

import com.seckillend.pojo.SeckillMessage;
import com.seckillend.result.ResultLP;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "message-service"/*, fallback = MessageFeignFallback.class*/)
public interface MessageFeign {

    @GetMapping("/miaosha-message/list/{userId}")
    public ResultLP<List<SeckillMessage>> messageList(@PathVariable Long userId);



}
