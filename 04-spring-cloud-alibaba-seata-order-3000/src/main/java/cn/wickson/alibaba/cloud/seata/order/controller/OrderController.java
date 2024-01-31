package cn.wickson.alibaba.cloud.seata.order.controller;

import cn.wickson.alibaba.cloud.seata.order.service.IOrderService;
import cn.wickson.cloud.alibaba.model.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/order")
public class OrderController {

    @Resource
    private IOrderService orderService;

    @PostMapping("/create")
    public void create(@RequestBody OrderDTO orderDTO) {
        orderService.create(orderDTO);
    }

}
