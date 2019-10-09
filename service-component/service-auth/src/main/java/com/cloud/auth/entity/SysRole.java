package com.cloud.auth.entity;

import com.cloud.common.base.BaseEntity;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 系统角色表
 * </p>
 *
 * @author zhuwj
 * @since 2019-08-31
 */
public class SysRole extends BaseEntity<SysRole> {


    private String roleDesc;

    private String roleName;

    private String roleCode;


    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "SysRole{" +
        "roleDesc=" + roleDesc +
        ", roleName=" + roleName +
        ", roleCode=" + roleCode +
        "}";
    }
}
