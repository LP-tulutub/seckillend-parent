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
 * @since 2020-08-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("order_info")
public class OrderInfo extends Model<OrderInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户ID
     */
    @TableField("USER_ID")
    private Long userId;

    /**
     * 商品ID
     */
    @TableField("GOODS_ID")
    private Long goodsId;

    /**
     * 收获地址ID
     */
    @TableField("DELIVERY_ADDR_ID")
    private Long deliveryAddrId;

    /**
     * 冗余过来的商品名称
     */
    @TableField("GOODS_NAME")
    private String goodsName;

    /**
     * 商品数量
     */
    @TableField("GOODS_COUNT")
    private Integer goodsCount;

    /**
     * 商品单价
     */
    @TableField("GOODS_PRICE")
    private BigDecimal goodsPrice;

    /**
     * 1PC，2ANDROID，3IOS
     */
    @TableField("ORDER_CHANNEL")
    private Integer orderChannel;

    /**
     * 订单状态，0新建未支付，1已支付，2已发货，3已收货，4已退款，5已完成
     */
    @TableField("STATUS")
    private Integer status;

    /**
     * 支付时间
     */
    @TableField("PAY_DATE")
    private Date payDate;

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
