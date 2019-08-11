package com.cloud.common.config;

import com.cloud.common.util.WebContextUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;


/**
 * Description: fegin拦截器配置 ，处理授权信息以及服务之间接口调用权限问题
 * Date:      2019/8/11 15:20
 * author     zhuwj
 * version    V1.0
 */
@Configuration
public class RequestInterceptorConfig implements RequestInterceptor {
    private final Logger logger = LoggerFactory.getLogger(RequestInterceptorConfig.class);

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String BEARER_TOKEN_TYPE = "Bearer";

    @Autowired
    private OAuth2RestTemplate oAuth2RestTemplate;

    @Override
    public void apply(RequestTemplate template) {
        String accessToken = WebContextUtil.getAccessToken();
        if(accessToken == null){
            accessToken =  oAuth2RestTemplate.getAccessToken().getValue();
        }
        logger.info("RequestInterceptorConfig accessToken :" +accessToken);
        template.header(AUTHORIZATION_HEADER,
                String.format("%s %s",
                        BEARER_TOKEN_TYPE,
                        accessToken));
    }
}
