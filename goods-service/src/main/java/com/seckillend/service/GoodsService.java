package com.seckillend.service;

import com.seckillend.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.seckillend.pojo.SecKillGoods;
import com.seckillend.result.ResultLP;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author root
 * @since 2020-08-13
 */
public interface GoodsService extends IService<Goods> {

    public ResultLP<List<SecKillGoods>> selSecKillGoodsByPage(Integer pagination, Integer size);

    public ResultLP<SecKillGoods> selGoodsSecKillById(Integer id);
}
