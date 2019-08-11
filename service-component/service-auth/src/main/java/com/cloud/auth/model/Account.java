package com.cloud.auth.model;

import com.cloud.common.base.BaseEntity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhuwj
 * @since 2019-08-10
 */
public class Account extends BaseEntity<Account> {


    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "Account{" +
        "username=" + username +
        ", password=" + password +
        "}";
    }
}
