//package com.cloud.auth.facade.user.impl;
//
//import com.cloud.auth.facade.user.UserFeginService;
//import com.cloud.auth.model.Account;
//import com.cloud.common.util.ResultConvertUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// * Description: 用户服务fegin实现
// * Date:      2019/8/11 9:02
// * author     zhuwj
// * version    V1.0
// */
//@Service
//public class UserFeginServiceImpl {
//
//    @Autowired
//    private UserFeginService userFeginService;
//
//
//    /**
//     * 用户信息查询
//     * @param username
//     * @return
//     */
//    public Account findByUsername(String username){
//        return ResultConvertUtil.resultConvert(userFeginService.findByUsername(username));
//    }
//}
