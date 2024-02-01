package cn.wickson.cloud.alibaba.seata.order.app.service;

import cn.wickson.cloud.alibaba.model.dto.OrderDTO;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
public interface IOrderAppService {

    /**
     * 创建订单
     *
     * @param orderDTO
     */
    void create(OrderDTO orderDTO);
}
