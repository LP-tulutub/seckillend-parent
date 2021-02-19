package com.seckillend.mapper;

import com.seckillend.entity.MiaoshaMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seckillend.pojo.SeckillMessage;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author root
 * @since 2020-08-20
 */
public interface MiaoshaMessageMapper extends BaseMapper<MiaoshaMessage> {

    @Select("select A.id,A.content,A.message_type,A.create_time,B.user_id,B.goods_id from miaosha_message A inner join miaosha_message_user B on A.id=B.message_id where B.user_id=#{userId}")
    List<SeckillMessage> uniteMiaoshaMessageUserSelByUserId(Long userId);
}
