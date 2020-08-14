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
@TableName("goods")
public class Goods extends Model<Goods> {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 商品名称
     */
    @TableField("GOODS_NAME")
    private String goodsName;

    /**
     * 商品标题
     */
    @TableField("GOODS_TITLE")
    private String goodsTitle;

    /**
     * 商品的图片
     */
    @TableField("GOODS_IMG")
    private String goodsImg;

    /**
     * 商品的详情介绍
     */
    @TableField("GOODS_DETAIL")
    private String goodsDetail;

    /**
     * 商品单价
     */
    @TableField("GOODS_PRICE")
    private BigDecimal goodsPrice;

    /**
     * 商品库存，-1表示没有限制
     */
    @TableField("GOODS_STOCK")
    private Integer goodsStock;

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
