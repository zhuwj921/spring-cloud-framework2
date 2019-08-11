package com.cloud.auth.security;

import com.cloud.auth.facade.user.impl.UserFeginServiceImpl;
import com.cloud.auth.model.Account;
import com.cloud.common.enums.ErrorCodeEnum;
import com.cloud.common.exception.BusinessException;
import com.cloud.common.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;


/**
 * Description: 用户详情实现
 * Date:      2019/8/8 23:02
 * author     user
 * version    V1.0
 */
@Service
public class SecurityUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserFeginServiceImpl userFeginService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = userFeginService.findByUsername(username);
        if(null == account){
            throw new BusinessException(ErrorCodeEnum.ACCOUNT_NO_EXIST);
        }
        Integer id = account.getId();
        String password = account.getPassword();
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        password= passwordEncoder.encode(password);
        Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("findclient"));

        UserInfo userInfo = new UserInfo();
        userInfo.setEnabled(true);
        userInfo.setAuthorities(grantedAuthorities);
        userInfo.setPassword(password);
        userInfo.setId(id);
        userInfo.setUsername(username);
        return userInfo;
    }
}
