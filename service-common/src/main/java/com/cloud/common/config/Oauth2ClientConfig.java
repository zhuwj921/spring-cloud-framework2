package com.cloud.common.config;

import com.cloud.common.properties.ProviderProberties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2RequestAuthenticator;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

/**
 * Description: oauth 客户端请求配置信息  持久化客户端请求信息
 * Date:      2019/8/11 15:36
 * author     zhuwj
 * version    V1.0
 */
@Configuration
public class Oauth2ClientConfig {

    @Autowired
    private ProviderProberties providerProberties;

    @Bean
    public  OAuth2RestTemplate oAuth2RestTemplate(){
        ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
        resourceDetails.setClientId(providerProberties.getClientId());
        resourceDetails.setClientSecret(providerProberties.getClientSecret());
        resourceDetails.setAccessTokenUri(providerProberties.getTokenInfoUri());
        resourceDetails.setId(providerProberties.getId());
        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(resourceDetails);
        oAuth2RestTemplate.setAuthenticator(new DefaultOAuth2RequestAuthenticator());
        return oAuth2RestTemplate;
    }
}
