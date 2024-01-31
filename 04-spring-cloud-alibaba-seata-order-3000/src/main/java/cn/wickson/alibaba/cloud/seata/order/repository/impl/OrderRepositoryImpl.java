package cn.wickson.alibaba.cloud.seata.order.repository.impl;

import cn.wickson.alibaba.cloud.seata.order.mapper.OrderMapper;
import cn.wickson.alibaba.cloud.seata.order.model.entity.Order;
import cn.wickson.alibaba.cloud.seata.order.repository.IOrderRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@Service
public class OrderRepositoryImpl extends ServiceImpl<OrderMapper, Order> implements IOrderRepository {
}
