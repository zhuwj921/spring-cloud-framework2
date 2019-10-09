package com.cloud.common.config;

import com.cloud.common.exception.BusinessException;
import com.cloud.common.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Description: 全局异常信息处理
 * Date:      2019/8/24 21:57
 * author     zhuwj
 * version    V1.0
 */
@Slf4j
@EnableWebMvc
@RestControllerAdvice
public class GlobalExceptionConfig {

    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseResult<String> errorHandler(Exception ex) {
        log.info("GlobalControllerAdvice errorHandler  Exception...",ex);
        return ResponseResult.error(ex.getMessage());
    }

    /**
     * 拦截捕捉自定义异常 BuessionException.class
     * @param ex
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    public  ResponseResult<String> errorHandler(BusinessException ex) {
        log.info("GlobalControllerAdvice errorHandler BusinessException ...",ex);
        return ResponseResult.error(ex.getCode(),ex.getMessage());
    }
}
