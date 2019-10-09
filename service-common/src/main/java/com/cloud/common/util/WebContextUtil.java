package com.cloud.common.util;

import cn.hutool.core.util.StrUtil;
import com.cloud.common.constants.SecurityConstants;
import com.cloud.common.security.UserInfo;
import lombok.experimental.UtilityClass;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Description: 获取当前上下文用户请求信息
 * Date:      2019/8/10 23:47
 * author     zhuwj
 * version    V1.0
 */
@UtilityClass
public  class WebContextUtil {

    /**
     * 获取当前上下文授权信息
     * @return
     */
    public  Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication;
        }
        return  null;
    }

    /**
     * 获取用户
     */
    public  UserInfo getUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserInfo) {
            return (UserInfo) principal;
        }
        return null;
    }

    /**
     * 获取用户
     */
    public  UserInfo getUser() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        return getUser(authentication);
    }

    /**
     * 获取当前用户id
     * @return
     */
    public  Integer getUserId(){
        return getUser().getId();
    }

    /**
     * 获取当前用户名称
     * @return
     */
    public  String getUsername(){
        return getUser().getUsername();
    }


    /**
     * 获取用户角色信息
     *
     * @return 角色集合
     */
    public List<Integer> getRoles() {
        Authentication authentication = getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<Integer> roleIds = new ArrayList<>();
        authorities.stream()
                .filter(granted -> StrUtil.startWith(granted.getAuthority(), SecurityConstants.ROLE))
                .forEach(granted -> {
                    String id = StrUtil.removePrefix(granted.getAuthority(), SecurityConstants.ROLE);
                    roleIds.add(Integer.parseInt(id));
                });
        return roleIds;
    }


    /**
     * 获取当前上下文token的信息
     * @return
     */
    public  OAuth2AuthenticationDetails getDetails(){
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
     *  获取当前登入用户的访问accessToken
     * @return
     */
    public  String getAccessToken(){
        if(null!= getDetails()){
            return getDetails().getTokenValue();
        }
        return null;
    }




    /**
     * 权限适配器方法
     * @return
     */
    private  UserInfo principalAdapter(){

//        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication)getAuthentication();
//        OAuth2Request oAuth2Request = oAuth2Authentication.getOAuth2Request();
//        String clientId = oAuth2Request.getClientId();
//        if(null == clientId){
//            LinkedHashMap<String,Object> linkedHashMap = (LinkedHashMap<String,Object> )oAuth2Authentication.getUserAuthentication().getDetails();
//            Object object =linkedHashMap.get("principal");
//            JSONObject jsonObject = JSONUtil.parseFromMap(((Map)object));
//            return jsonObject.toBean(UserInfo.class);
//        }else{
//            if (principal instanceof UserInfo) {
//                return (UserInfo) principal;
//            }
//            return  (UserInfo)oAuth2Authentication.getPrincipal();
//        }

        return null;
    }

}
