package com.cloud.auth.security;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cloud.auth.entity.SysUser;
import com.cloud.auth.service.ISysUserService;
import com.cloud.common.enums.ErrorCodeEnum;
import com.cloud.common.exception.BusinessException;
import com.cloud.common.security.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;


/**
 * Description: 用户详情实现
 * Date:      2019/8/8 23:02
 * author     zhuwj
 * version    V1.0
 */
@Service
public class SecurityUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ISysUserService sysUserService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserService.getOne(Wrappers.<SysUser>query()
                .lambda().eq(SysUser::getUsername, username));
        if(null == user){
            throw new BusinessException(ErrorCodeEnum.ACCOUNT_NO_EXIST);
        }
        Integer id = user.getId();
        String password = user.getPassword();
        Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();
        // 1. 放入角色时需要加前缀ROLE_，而在controller使用时不需要加ROLE_前缀
        // 2. 放入的是权限时，不能加ROLE_前缀，hasAuthority与放入的权限名称对应即可
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_1"));
        grantedAuthorities.add(new SimpleGrantedAuthority("user:account:findByUsername"));
        UserInfo userInfo = new UserInfo(id,"zhuwj","","",username,password,true,true,true,true,grantedAuthorities);
        return userInfo;
    }
}
