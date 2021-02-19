package com.seckillend.feign.fallback;

import com.seckillend.feign.GoodsFeign;
import com.seckillend.enums.ResultStatus;
import com.seckillend.pojo.SecKillGoods;
import com.seckillend.result.ResultLP;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GoodsFeignFallback implements GoodsFeign {
    @Override
    public ResultLP<List<SecKillGoods>> goodsList(Integer pagination, Integer size) {
        return ResultLP.error(ResultStatus.PATH_FEIGN_FALLBACK);
    }

    @Override
    public ResultLP<SecKillGoods> goodsDetail(Integer id) {
        return ResultLP.error(ResultStatus.PATH_FEIGN_FALLBACK);
    }
}
