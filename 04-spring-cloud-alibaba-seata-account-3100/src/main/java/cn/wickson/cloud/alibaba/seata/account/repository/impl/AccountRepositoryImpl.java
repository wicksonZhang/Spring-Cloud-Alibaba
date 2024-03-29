package cn.wickson.cloud.alibaba.seata.account.repository.impl;

import cn.wickson.cloud.alibaba.seata.account.entity.Account;
import cn.wickson.cloud.alibaba.seata.account.mapper.AccountMapper;
import cn.wickson.cloud.alibaba.seata.account.repository.IAccountRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@Service
public class AccountRepositoryImpl extends ServiceImpl<AccountMapper, Account> implements IAccountRepository {
}
