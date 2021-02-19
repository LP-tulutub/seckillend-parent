package com.seckillend.feign.fallback;

import com.seckillend.enums.ResultStatus;
import com.seckillend.feign.OrderFeign;
import com.seckillend.pojo.SeckillOrderGoods;
import com.seckillend.result.ResultLP;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderFeignFallback implements OrderFeign {
    @Override
    public ResultLP<List<SeckillOrderGoods>> list(Long id) {
        return ResultLP.error(ResultStatus.PATH_FEIGN_FALLBACK);
    }
}
