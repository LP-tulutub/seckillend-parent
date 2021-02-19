package com.seckillend.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class SeckillOrderGoods implements Serializable {

    private Long id;
    private Long userId;
    private Long goodsId;
    private Long deliveryAddrId;
    private String goodsName;
    private Integer goodsCount;
    private BigDecimal goodsPrice;
    private Integer orderChannel;
    private Integer status;
    private Date payDate;
    private Date createTime;
    private Date updateTime;

    private SecKillGoods goods;
}
