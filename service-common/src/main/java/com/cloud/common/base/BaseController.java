package com.cloud.common.base;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import com.cloud.common.constants.GlobalContanst;
import com.cloud.common.util.WebContextUtil;

/**
 * Description: 基础控制器
 * Date:      2019/8/10 22:30
 * author     zhuwj
 * version    V1.0
 */
public class BaseController implements GlobalContanst {

    /**
     * 获取当前登入用户账号名称
     * @return
     */
    public String getUsername() {

        return WebContextUtil.getUsername();
    }

    /**
     * 获取当前用户id
     * @return
     */
    public Integer getUserId(){
        return WebContextUtil.getUserId();
    }


    public String getUuid(){
        return IdUtil.simpleUUID();
    }
}
