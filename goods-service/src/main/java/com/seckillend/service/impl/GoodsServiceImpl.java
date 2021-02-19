package com.seckillend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seckillend.entity.Goods;
import com.seckillend.mapper.GoodsMapper;
import com.seckillend.pojo.SecKillGoods;
import com.seckillend.result.ResultLP;
import com.seckillend.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author root
 * @since 2020-08-13
 */
@Service
@Slf4j
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public ResultLP<List<SecKillGoods>> selSecKillGoodsByPage(Integer pagination, Integer size) {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        Page<Goods> page = new Page<>();
        page.setSize(size);
        page.setCurrent(pagination);

        ResultLP<List<SecKillGoods>> resultLP = ResultLP.build();
        resultLP.setData(this.goodsMapper.uniteMiaoshaGoodsSelByPage(page, wrapper));
        return resultLP;
    }

    @Override
    public ResultLP<SecKillGoods> selGoodsSecKillById(Integer id) {
        ResultLP<SecKillGoods> resultLP = ResultLP.build();
        resultLP.setData(this.goodsMapper.uniteMiaoshaGoodsSelById(id));
        return resultLP;
    }
}
