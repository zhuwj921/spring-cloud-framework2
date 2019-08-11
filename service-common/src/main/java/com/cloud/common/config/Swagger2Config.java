package com.cloud.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: Swagger2Config 配置文件
 * Date:      2019/8/10 22:30
 * author     zhuwj
 * version    V1.0
 */
@Configuration
@EnableSwagger2
public class Swagger2Config  {

    private static final Logger logger = LoggerFactory.getLogger(Swagger2Config.class);

    private static String defaultToken = "Bearer  ";

    @Autowired
    private OAuth2RestTemplate oAuth2RestTemplate;


    @Bean
    public Docket createRestApi() {
        logger.info("Swagger2Configuration createRestApi() ");
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("Authorization").description("令牌格式: Bearer accessToken").defaultValue(defaultToken).modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cloud"))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(pars);
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("spring cloud微服务api文档")
                .description("spring cloud微服务api文档")
                .termsOfServiceUrl("")
                .version("2.0")
                .build();
    }


}
