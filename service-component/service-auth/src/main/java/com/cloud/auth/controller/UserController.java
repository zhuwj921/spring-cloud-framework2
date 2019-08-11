package com.cloud.auth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Description: 用户信息控制器
 * Date:      2019/8/11 14:48
 * author     zhuwj
 * version    V1.0
 */
@RestController
public class UserController {

    @ApiOperation(value = "服务端验证接口", notes = "服务端验证接口")
    @GetMapping("user")
    public Principal user(Principal user){
        return user;
    }
}
