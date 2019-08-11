package com.cloud.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Description: oauth 客户端请求配置属性读取
 * Date:      2019/8/11 15:36
 * author     zhuwj
 * version    V1.0
 */
@Configuration
@ConfigurationProperties(
        prefix = "spring.provider"
)
@Component
public class ProviderProberties {

    private  String id;

    private  String clientId;

    private  String clientSecret;

    private  String tokenInfoUri;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getTokenInfoUri() {
        return tokenInfoUri;
    }

    public void setTokenInfoUri(String tokenInfoUri) {
        this.tokenInfoUri = tokenInfoUri;
    }


}
