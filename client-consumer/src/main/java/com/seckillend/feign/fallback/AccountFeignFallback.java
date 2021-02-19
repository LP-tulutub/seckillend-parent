package com.seckillend.feign.fallback;

import com.seckillend.enums.ResultStatus;
import com.seckillend.feign.AccountFeign;
import com.seckillend.pojo.Account;
import com.seckillend.result.ResultLP;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AccountFeignFallback implements AccountFeign {

    @Override
    public ResultLP<Object> login(Account account) {
        return ResultLP.error(ResultStatus.PATH_FEIGN_FALLBACK);
    }

    @Override
    public ResultLP<Object> register(Map map) {
        return ResultLP.error(ResultStatus.PATH_FEIGN_FALLBACK);
    }
}
