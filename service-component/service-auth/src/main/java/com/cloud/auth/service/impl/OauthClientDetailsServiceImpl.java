package com.cloud.auth.service.impl;

import com.cloud.auth.entity.OauthClientDetails;
import com.cloud.auth.mapper.OauthClientDetailsMapper;
import com.cloud.auth.service.IOauthClientDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhuwj
 * @since 2019-08-11
 */
@Service
public class OauthClientDetailsServiceImpl extends ServiceImpl<OauthClientDetailsMapper, OauthClientDetails> implements IOauthClientDetailsService {

}
