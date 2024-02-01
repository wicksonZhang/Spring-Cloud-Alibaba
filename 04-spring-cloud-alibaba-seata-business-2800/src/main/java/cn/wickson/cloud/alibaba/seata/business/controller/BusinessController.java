package cn.wickson.cloud.alibaba.seata.business.controller;

import cn.wickson.cloud.alibaba.seata.business.app.service.IBusinessAppService;
import cn.wickson.cloud.alibaba.model.vo.BusinessVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Business - 控制类
 *
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/business")
public class BusinessController {

    @Resource
    private IBusinessAppService businessService;

    @PostMapping(value = "/purchase")
    public void purchase(@RequestBody BusinessVO businessVO) {
        businessService.purchase(businessVO);
    }

}
