package com.example.cryoem.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.example.cryoem.vo.UserVO;
import lombok.Builder;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
@Builder
public class User implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 电话
     */
    @TableField(value = "phone")
    private String phone;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public static User buildUser(UserVO userVO) {
        return User.builder()
                .id(IdWorker.getId())
                .username(userVO.getUsername())
                .password(userVO.getPassword())
                .email(userVO.getEmail())
                .build();
    }
}