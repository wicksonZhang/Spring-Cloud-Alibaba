package cn.wickson.cloud.alibaba.seata.account.controller;

import cn.wickson.cloud.alibaba.seata.account.service.IAccountService;
import cn.wickson.cloud.alibaba.model.dto.AccountDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @GetMapping("/list")
    public List<AccountDTO> listAll() {
        return accountService.listAllByAccount();
    }

}
