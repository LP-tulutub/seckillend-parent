package com.seckillend.service;

import com.seckillend.entity.MiaoshaMessage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.seckillend.pojo.SeckillMessage;
import com.seckillend.result.ResultLP;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author root
 * @since 2020-08-20
 */
public interface MiaoshaMessageService extends IService<MiaoshaMessage> {

    public List<SeckillMessage> messageList(Long userId);

}
