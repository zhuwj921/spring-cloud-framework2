package com.cloud.common.util;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cloud.common.model.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 获取当前上下文用户请求信息
 * Date:      2019/8/10 23:47
 * author     zhuwj
 * version    V1.0
 */
public final class WebContextUtil {

    /**
     * 获取当前上下文授权信息
     * @return
     */
    public static Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication;
        }
        return  null;
    }


    /**
     * 获取当前上下文token的信息
     * @return
     */
    public static OAuth2AuthenticationDetails getDetails(){
        Authentication authentication = getAuthentication();
        if(null!=authentication){

            OAuth2AuthenticationDetails details = null;
            try {
                details = (OAuth2AuthenticationDetails) authentication.getDetails();
            } catch (Exception e) {
            }
            return details;
        }
        return null;
    }

    /**
     * 获取当前用户信息
     * @return
     */
    public static UserInfo getUser(){
       return principalAdapter();
    }



    /**
     *  获取当前登入用户的访问accessToken
     * @return
     */
    public static String getAccessToken(){
        if(null!= getDetails()){
            return getDetails().getTokenValue();
        }
        return null;
    }

    /**
     * 获取当前用户id
     * @return
     */
    public static Integer getUserId(){
        return getUser().getId();
    }

    /**
     * 获取当前用户名称
     * @return
     */
    public static String getUsername(){
        return getUser().getUsername();
    }





    /**
     * 权限适配器方法
     * @return
     */
    private static UserInfo principalAdapter(){

        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication)getAuthentication();
        OAuth2Request oAuth2Request = oAuth2Authentication.getOAuth2Request();
        String clientId = oAuth2Request.getClientId();
        if(null == clientId){
            LinkedHashMap<String,Object> linkedHashMap = (LinkedHashMap<String,Object> )oAuth2Authentication.getUserAuthentication().getDetails();
            Object object =linkedHashMap.get("principal");
            JSONObject jsonObject = JSONUtil.parseFromMap(((Map)object));
            return jsonObject.toBean(UserInfo.class);
        }else{
            return  (UserInfo)oAuth2Authentication.getPrincipal();
        }

    }

}
