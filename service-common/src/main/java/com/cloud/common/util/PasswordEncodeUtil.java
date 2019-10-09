package com.cloud.common.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Description: 密码加密验证工具
 * Date:      2019/8/10 23:47
 * author     zhuwj
 * version    V1.0
 */
public class PasswordEncodeUtil {

    public static String encode(String passowrd){
        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        return encoder.encode(passowrd);
    }

    public static boolean validatePassword(String password,String oldPassword){
        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        return encoder.matches(password,oldPassword);
    }
}
