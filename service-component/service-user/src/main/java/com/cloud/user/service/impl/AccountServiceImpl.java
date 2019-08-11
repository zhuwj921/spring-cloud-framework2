package com.cloud.user.service.impl;

import com.cloud.user.entity.Account;
import com.cloud.user.mapper.AccountMapper;
import com.cloud.user.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhuwj
 * @since 2019-08-10
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

}
