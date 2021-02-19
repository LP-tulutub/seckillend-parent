package com.seckillend.service.impl;

import com.seckillend.entity.OrderInfo;
import com.seckillend.mapper.OrderInfoMapper;
import com.seckillend.pojo.SeckillOrderGoods;
import com.seckillend.service.OrderInfoService;
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
 * @since 2020-08-21
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Override
    public List<SeckillOrderGoods> oneToOneByUserId(Long id) {
        return this.orderInfoMapper.oneToOneByUserId(id);
    }
}
