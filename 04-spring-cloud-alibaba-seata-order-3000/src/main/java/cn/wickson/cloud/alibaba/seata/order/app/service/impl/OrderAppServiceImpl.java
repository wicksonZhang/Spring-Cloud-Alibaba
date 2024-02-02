package cn.wickson.cloud.alibaba.seata.order.app.service.impl;

import cn.hutool.core.lang.UUID;
import cn.wickson.cloud.alibaba.model.dto.AccountDTO;
import cn.wickson.cloud.alibaba.model.dto.OrderDTO;
import cn.wickson.cloud.alibaba.seata.order.app.service.AbstractOrderAppService;
import cn.wickson.cloud.alibaba.seata.order.app.service.IOrderAppService;
import cn.wickson.cloud.alibaba.seata.order.convert.OrderConvert;
import cn.wickson.cloud.alibaba.seata.order.model.entity.Order;
import cn.wickson.cloud.alibaba.utils.ParamFormatUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@Service
public class OrderAppServiceImpl extends AbstractOrderAppService implements IOrderAppService {

    @Override
    @Transactional(isolation= Isolation.REPEATABLE_READ,rollbackFor = Exception.class)
    public void create(final OrderDTO orderDTO) {
        /* Step-1: 参数校验 */
        ParamFormatUtil.formatParam(orderDTO);

        /* Step-2: 减少账户余额 */
        AccountDTO accountDTO = OrderConvert.INSTANCE.toAccountDTO(orderDTO);
        this.delAccount(accountDTO);

        /* Step-3： 创建订单 */
        Order order = OrderConvert.INSTANCE.toOrderDO(orderDTO);
        order.setOrderNo(UUID.randomUUID().toString());
        this.orderRepository.save(order);
    }

    @Override
    public List<OrderDTO> listAllByOrder() {
        List<Order> orderList = this.orderRepository.list();
        return OrderConvert.INSTANCE.toOrderDTOList(orderList);
    }
}
