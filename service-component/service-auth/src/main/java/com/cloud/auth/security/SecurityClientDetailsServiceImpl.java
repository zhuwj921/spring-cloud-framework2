package com.cloud.auth.security;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cloud.auth.entity.OauthClientDetails;
import com.cloud.auth.entity.SysUser;
import com.cloud.auth.service.IOauthClientDetailsService;
import com.cloud.common.util.PasswordEncodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

/**
 * Description: 客户端实现
 * Date:      2019/8/10 16:28
 * author     user
 * version    V1.0
 */
@Service
public class SecurityClientDetailsServiceImpl implements ClientDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(SecurityClientDetailsServiceImpl.class);

    @Autowired
    private IOauthClientDetailsService oauthClientDetailsService;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        logger.info("当前登入客户端->"+clientId);
        OauthClientDetails oauthClientDetails = oauthClientDetailsService.getOne(Wrappers.<OauthClientDetails>query()
                .lambda().eq(OauthClientDetails::getClientId, clientId));

        String clientSecret = oauthClientDetails.getClientSecret();
        String resourceIds = oauthClientDetails.getResourceIds();
        String scopes = oauthClientDetails.getScope();
        String grantTypes = oauthClientDetails.getAuthorizedGrantTypes();
        String authorities = oauthClientDetails.getAuthorities();
        String redirectUris = oauthClientDetails.getWebServerRedirectUri();
        Integer refreshTokenValiditySeconds = oauthClientDetails.getRefreshTokenValidity();
        Integer accessTokenValiditySeconds = oauthClientDetails.getAccessTokenValidity();
        BaseClientDetails baseClientDetails = new BaseClientDetails(clientId,resourceIds,scopes,grantTypes,authorities,redirectUris);
        baseClientDetails.setRefreshTokenValiditySeconds(refreshTokenValiditySeconds);
        baseClientDetails.setAccessTokenValiditySeconds(accessTokenValiditySeconds);
        baseClientDetails.setClientId(clientId);
        clientSecret= PasswordEncodeUtil.encode(clientSecret);
        baseClientDetails.setClientSecret(clientSecret);
        return baseClientDetails;
    }
}
