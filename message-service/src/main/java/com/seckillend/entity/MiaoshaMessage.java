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
 * @since 2020-08-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("miaosha_message")
public class MiaoshaMessage extends Model<MiaoshaMessage> {

    private static final long serialVersionUID = 1L;

    /**
     * 消息主键
     */
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 消息内容
     */
    @TableField("CONTENT")
    private String content;

    /**
     * 1 有效 2 失效 
     */
    @TableField("STATUS")
    private Integer status;

    /**
     * 结束时间
     */
    @TableField("OVER_TIME")
    private Date overTime;

    /**
     * 0 秒杀消息 1 购买消息 2 推送消息
     */
    @TableField("MESSAGE_TYPE")
    private Integer messageType;

    /**
     * 发送类型 0 APP 1 PC 2 IOS
     */
    @TableField("SEND_TYPE")
    private Integer sendType;

    /**
     * 商品名称
     */
    @TableField("GOOD_NAME")
    private String goodName;

    /**
     * 商品价格
     */
    @TableField("PRICE")
    private BigDecimal price;

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
