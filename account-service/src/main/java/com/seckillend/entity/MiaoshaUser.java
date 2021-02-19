package com.seckillend.entity;

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
 * @since 2020-08-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("miaosha_user")
public class MiaoshaUser extends Model<MiaoshaUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID，手机号码
     */
    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("NICKNAME")
    private String nickname;

    /**
     * MD5(MD5(PASS明文+固定SALT) + SALT)
     */
    @TableField("PASSWORD")
    private String password;

    @TableField("SALT")
    private String salt;

    /**
     * 头像，云存储的ID
     */
    @TableField("HEAD")
    private String head;

    /**
     * 上蔟登录时间
     */
    @TableField("LAST_LOGIN_DATE")
    private Date lastLoginDate;

    /**
     * 登录次数
     */
    @TableField("LOGIN_COUNT")
    private Integer loginCount;

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
