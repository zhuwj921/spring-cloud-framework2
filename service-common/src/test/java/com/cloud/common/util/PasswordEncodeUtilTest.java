package com.cloud.common.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Description: TODO
 * Date:      2019/8/25 12:28
 * author     zhuwj
 * version    V1.0
 */
public class PasswordEncodeUtilTest {

    @Test
    public void  encode(){
        System.err.println(PasswordEncodeUtil.encode("admin"));
    }

    @Test
    public void  validatePassword(){
        System.err.println(PasswordEncodeUtil.validatePassword("admin","$2a$10$J5.iZkZhc.IJyHXdfmekCuUs.vwa/FcqSvCMRI2Wif8tT87niatc6"));
    }

}