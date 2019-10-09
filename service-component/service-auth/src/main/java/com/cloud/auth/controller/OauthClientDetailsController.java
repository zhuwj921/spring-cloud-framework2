package com.cloud.auth.controller;


import com.cloud.auth.entity.OauthClientDetails;
import com.cloud.auth.service.IOauthClientDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.cloud.common.base.BaseController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhuwj
 * @since 2019-08-31
 */
@RestController
@RequestMapping("/oauthClientDetails")
public class OauthClientDetailsController extends BaseController {

    @Autowired
    private  IOauthClientDetailsService oauthClientDetailsService;

    @RequestMapping("/findAll")
    public List<OauthClientDetails> findAll(){
        return oauthClientDetailsService.list();
    }

}

