package cn.wickson.cloud.alibaba.seata.order.controller;

import cn.wickson.cloud.alibaba.handle.GlobalSentinelExceptionHandler;
import cn.wickson.cloud.alibaba.model.dto.OrderDTO;
import cn.wickson.cloud.alibaba.seata.order.app.service.IOrderAppService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
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
@RequestMapping("/order")
public class OrderController {

    @Resource
    private IOrderAppService orderService;

    @PostMapping("/create")
    @SentinelResource(value = "customerBlockHandler", blockHandlerClass = GlobalSentinelExceptionHandler.class, blockHandler = "handleOrderCreateException")
    public void create(@RequestBody OrderDTO orderDTO) {
        orderService.create(orderDTO);
    }

    @GetMapping("/list")
    public List<OrderDTO> listAll() {
        return orderService.listAllByOrder();
    }
}
