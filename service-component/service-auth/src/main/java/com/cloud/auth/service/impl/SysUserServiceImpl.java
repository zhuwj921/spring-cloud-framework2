package com.cloud.auth.service.impl;

import com.cloud.auth.entity.SysUser;
import com.cloud.auth.mapper.SysUserMapper;
import com.cloud.auth.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhuwj
 * @since 2019-08-31
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
