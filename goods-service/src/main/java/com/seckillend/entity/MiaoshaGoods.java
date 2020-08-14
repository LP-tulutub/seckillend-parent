package com.seckillend.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author root
 * @since 2020-08-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("miaosha_goods")
public class MiaoshaGoods extends Model<MiaoshaGoods> {

    private static final long serialVersionUID = 1L;

    /**
     * 秒杀的商品表
     */
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 商品ID
     */
    @TableField("GOODS_ID")
    private Long goodsId;

    /**
     * 秒杀价
     */
    @TableField("MIAOSHA_PRICE")
    private BigDecimal miaoshaPrice;

    /**
     * 库存数量
     */
    @TableField("STOCK_COUNT")
    private Integer stockCount;

    /**
     * 秒杀开始时间
     */
    @TableField("START_DATE")
    private Date startDate;

    /**
     * 秒杀结束时间
     */
    @TableField("END_DATE")
    private Date endDate;

    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField("DEL_STATUS")
    @TableLogic
    private Integer delStatus;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
