package com.cloud.auth.vo;

import com.cloud.auth.entity.SysUser;
import lombok.Data;

import java.io.Serializable;

/**
 * Description: 前端用户展示信息
 * Date:      2019/8/31 19:33
 * author     zhuwj
 * version    V1.0
 */
@Data
public class UserInfoVO implements Serializable {

    /**
     * 用户基本信息
     */
    private SysUser sysUser;
    /**
     * 权限标识集合
     */
    private String[] permissions;

    /**
     * 角色集合
     */
    private Integer[] roles;
}
