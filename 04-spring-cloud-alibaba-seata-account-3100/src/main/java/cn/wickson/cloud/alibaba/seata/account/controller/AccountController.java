package cn.wickson.cloud.alibaba.seata.account.controller;

import cn.wickson.cloud.alibaba.seata.account.service.IAccountService;
import cn.wickson.cloud.alibaba.model.dto.AccountDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    private IAccountService accountService;

    @PutMapping("/debit")
    public void debit(@RequestBody AccountDTO accountDTO) {
        accountService.debit(accountDTO);
    }

}
