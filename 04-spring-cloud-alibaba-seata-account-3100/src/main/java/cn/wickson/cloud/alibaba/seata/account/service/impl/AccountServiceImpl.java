package cn.wickson.cloud.alibaba.seata.account.service.impl;

import cn.hutool.core.util.ObjUtil;
import cn.wickson.cloud.alibaba.seata.account.convert.AccountConvert;
import cn.wickson.cloud.alibaba.seata.account.entity.Account;
import cn.wickson.cloud.alibaba.seata.account.repository.IAccountRepository;
import cn.wickson.cloud.alibaba.seata.account.service.IAccountService;
import cn.wickson.cloud.alibaba.enums.ResultCodeEnum;
import cn.wickson.cloud.alibaba.exception.UserOperationException;
import cn.wickson.cloud.alibaba.model.dto.AccountDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author ZhangZiHeng
 * @date 2024-02-01
 */
@Service
public class AccountServiceImpl implements IAccountService {

    @Resource
    private IAccountRepository accountRepository;

    @Override
    @Transactional(isolation= Isolation.REPEATABLE_READ,rollbackFor = Exception.class)
    public void debit(AccountDTO accountDTO) {
        Long userId = accountDTO.getUserId();
        Account account = accountRepository.lambdaQuery().eq(Account::getUserId, userId).one();
        if (ObjUtil.isNull(account)) {
            throw UserOperationException.getInstance(ResultCodeEnum.SEATA_ACCOUNT_NULL_POINT_EXCEPTION);
        }
        BigDecimal amount = account.getAmount();
        BigDecimal accountAmount = accountDTO.getAmount();
        if (amount.compareTo(accountAmount) < 0) {
            throw UserOperationException.getInstance(ResultCodeEnum.SEATA_ACCOUNT_INSUFFICIENT_BALANCE);
        }
        account.setAmount(amount.subtract(accountAmount));
        accountRepository.updateById(account);
    }

    @Override
    public List<AccountDTO> listAllByAccount() {
        List<Account> accountList = accountRepository.list();
        return AccountConvert.INSTANCE.toDTO(accountList);
    }
}
