package cn.wickson.alibaba.cloud.seata.account.repository.impl;

import cn.wickson.alibaba.cloud.seata.account.entity.Account;
import cn.wickson.alibaba.cloud.seata.account.mapper.AccountMapper;
import cn.wickson.alibaba.cloud.seata.account.repository.IAccountRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@Service
public class AccountRepositoryImpl extends ServiceImpl<AccountMapper, Account> implements IAccountRepository {
}
