package com.cloud.common.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Description: 扩展用户信息
 * Date:      2019/8/24 22:28
 * author     zhuwj
 * version    V1.0
 */
public class UserInfo extends User {

    /**
     *  用户id
     */
    private Integer id;

    /**
     *  用户昵称
     */
    private String nickName;

    /**
     * 用户电话号码
     */
    private String phone;

    /**
     *  用户头像
     */
    private String head;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }


    public UserInfo(Integer id,String nickName,String phone, String head,String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.nickName = nickName;
        this.phone = phone;
        this.head = head;
    }



}
