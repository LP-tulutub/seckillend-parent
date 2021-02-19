package com.seckillend.feign;

import com.seckillend.feign.fallback.AccountFeignFallback;
import com.seckillend.pojo.Account;
import com.seckillend.result.ResultLP;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "account-service", fallback = AccountFeignFallback.class)
public interface AccountFeign {

    @PostMapping("/account/login")
    ResultLP<Object> login(@RequestBody Account account);

    @PostMapping("/account/register")
    ResultLP<Object> register(@RequestBody Map map);
}
