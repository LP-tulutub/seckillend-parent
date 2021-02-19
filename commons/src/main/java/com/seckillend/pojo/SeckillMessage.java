package com.seckillend.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SeckillMessage  implements Serializable {

    private Long id;
    private String content;
    private Integer messageType;
    private Date createTime;
    private Long userId;
    private Integer goodId;

}
