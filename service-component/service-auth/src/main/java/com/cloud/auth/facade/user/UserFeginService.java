package com.cloud.auth.facade.user;

import com.cloud.auth.model.Account;
import com.cloud.common.response.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Description: 用户服务
 * Date:      2019/8/10 23:55
 * author     zhuwj
 * version    V1.0
 */
@FeignClient(value = "service-user")
public interface UserFeginService {



    @RequestMapping(value = "/account/findByUsername/{username}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseResult<Account> findByUsername(@PathVariable("username") String username);

}
