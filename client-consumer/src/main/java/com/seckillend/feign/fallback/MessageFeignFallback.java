package com.seckillend.feign.fallback;

import com.seckillend.enums.ResultStatus;
import com.seckillend.feign.MessageFeign;
import com.seckillend.pojo.SeckillMessage;
import com.seckillend.result.ResultLP;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class MessageFeignFallback implements MessageFeign {

    @Override
    public ResultLP<List<SeckillMessage>> messageList(Long userId) {
        return ResultLP.error(ResultStatus.PATH_FEIGN_FALLBACK);
    }
}
