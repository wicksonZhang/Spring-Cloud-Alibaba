package cn.wickson.alibaba.cloud.seata.order.service;

import cn.wickson.cloud.alibaba.model.dto.OrderDTO;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
public interface IOrderService {

    /**
     * 创建订单
     *
     * @param orderDTO
     */
    void create(OrderDTO orderDTO);
}
