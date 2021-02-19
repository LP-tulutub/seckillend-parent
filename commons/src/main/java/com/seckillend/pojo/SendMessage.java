package com.seckillend.pojo;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SendMessage implements Serializable {

    private Long userId;
    private Long goodsId;
    private String goodsName;
    private BigDecimal miaoshaPrice;
}
