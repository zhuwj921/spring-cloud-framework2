package com.cloud.common.util;

import com.cloud.common.enums.ErrorCodeEnum;
import com.cloud.common.exception.BusinessException;
import com.cloud.common.response.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: 返回结果转换工具
 * Date:      2019/8/11 9:04
 * author     zhuwj
 * version    V1.0
 */
public class ResultConvertUtil {
    private static final Logger logger = LoggerFactory.getLogger(ResultConvertUtil.class);

    public static <T> T resultConvert(ResponseResult<T> responseResult){

        if(responseResult.getCode().equals(ErrorCodeEnum.RESULT_SUCCESS.getCode())){
            return responseResult.getResult();
        }
        throw new BusinessException(responseResult.getCode(),responseResult.getMsg());
    }
}
