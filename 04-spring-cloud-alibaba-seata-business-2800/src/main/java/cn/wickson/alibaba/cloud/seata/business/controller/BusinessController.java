package cn.wickson.alibaba.cloud.seata.business.controller;

import cn.wickson.alibaba.cloud.seata.business.service.IBusinessService;
import cn.wickson.cloud.alibaba.model.dto.BusinessDTO;
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
    private IBusinessService businessService;

    @PostMapping(value = "/purchase")
    public void purchase(@RequestBody BusinessDTO businessDTO) {
        businessService.purchase(businessDTO);
    }

}
