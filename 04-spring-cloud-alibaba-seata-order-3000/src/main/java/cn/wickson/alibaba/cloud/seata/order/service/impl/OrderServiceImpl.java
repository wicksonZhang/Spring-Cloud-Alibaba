package cn.wickson.alibaba.cloud.seata.order.service.impl;

import cn.wickson.alibaba.cloud.seata.order.feign.ApiAccountFeign;
import cn.wickson.alibaba.cloud.seata.order.model.entity.Order;
import cn.wickson.alibaba.cloud.seata.order.repository.IOrderRepository;
import cn.wickson.alibaba.cloud.seata.order.service.IOrderService;
import cn.wickson.cloud.alibaba.model.dto.AccountDTO;
import cn.wickson.cloud.alibaba.model.dto.OrderDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Resource
    private IOrderRepository orderRepository;

    @Resource
    private ApiAccountFeign accountFeign;

    @Override
    public void create(OrderDTO orderDTO) {
        AccountDTO accountDTO = new AccountDTO();

        accountFeign.debit(accountDTO);

        Order order = new Order();
        order.setUserId(orderDTO.getUserId());
        order.setCommodityCode(orderDTO.getCommodityCode());
        order.setCount(orderDTO.getOrderCount());
        order.setAmount(orderDTO.getOrderAmount());

        // INSERT INTO orders ...
        orderRepository.save(order);
    }

}
