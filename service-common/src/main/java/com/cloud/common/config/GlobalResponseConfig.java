package com.cloud.common.config;

import com.cloud.common.constants.GlobalContanst;
import com.cloud.common.enums.ErrorCodeEnum;
import com.cloud.common.response.ResponseResult;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.LinkedHashMap;

/**
 * Description: 全局返回结果定义
 * Date:      2019/8/24 21:30
 * author     zhuwj
 * version    V1.0
 */

@RestControllerAdvice
public class GlobalResponseConfig implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        ResponseResult responseResult = new ResponseResult();
        //自定义返回结果信息展示 不处理
        if(body instanceof  ResponseResult){
            responseResult = (ResponseResult) body;
        }else if (body instanceof LinkedHashMap){
            LinkedHashMap linkedHashMap = (LinkedHashMap) body;
            String status = linkedHashMap.get("status").toString();
            responseResult.setCode(Integer.valueOf(status));
            responseResult.setMsg(linkedHashMap.get("message").toString());
            responseResult.setTimestamp(System.currentTimeMillis());
        } else{
            responseResult.setCode(ErrorCodeEnum.RESULT_SUCCESS.getCode());
            responseResult.setMsg(ErrorCodeEnum.RESULT_SUCCESS.getMsg());
            responseResult.setResult(body);
            responseResult.setTimestamp(System.currentTimeMillis());
        }
        return responseResult;
    }
}
