package cn.wickson.cloud.alibaba.seata.account.service;

import cn.wickson.cloud.alibaba.model.dto.AccountDTO;

/**
 * @author ZhangZiHeng
 * @date 2024-02-01
 */
public interface IAccountService {

    void debit(AccountDTO accountDTO);

}
