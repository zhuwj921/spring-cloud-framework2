package com.cloud.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloud.common.response.ResponseResult;
import com.cloud.common.util.WebContextUtil;
import com.cloud.user.entity.Account;
import com.cloud.user.service.IAccountService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.cloud.common.base.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhuwj
 * @since 2019-08-10
 */
@RestController
@RequestMapping("/account")
public class AccountController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private IAccountService accountService;

    @ApiOperation(value = "用户信息查询", notes = "通过用户名查询用户信息")
    @GetMapping("/findByUsername/{username}")
    public ResponseResult<Account> findByUsername(@PathVariable("username") String username){
        QueryWrapper<Account> ew = new QueryWrapper<Account>();
        ew.eq("username",username);
        //WebContextUtil.getUser();
        return ResponseResult.ok(accountService.getOne(ew));
    }
}

