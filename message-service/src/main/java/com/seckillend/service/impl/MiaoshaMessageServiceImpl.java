package com.seckillend.service.impl;

import com.seckillend.entity.MiaoshaMessage;
import com.seckillend.mapper.MiaoshaMessageMapper;
import com.seckillend.pojo.SeckillMessage;
import com.seckillend.result.ResultLP;
import com.seckillend.service.MiaoshaMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author root
 * @since 2020-08-20
 */
@Service
public class MiaoshaMessageServiceImpl extends ServiceImpl<MiaoshaMessageMapper, MiaoshaMessage> implements MiaoshaMessageService {

    @Resource
    private MiaoshaMessageMapper miaoshaMessageMapper;

    @Override
    public List<SeckillMessage> messageList(Long userId) {
        return this.miaoshaMessageMapper.uniteMiaoshaMessageUserSelByUserId(userId);
    }
}
