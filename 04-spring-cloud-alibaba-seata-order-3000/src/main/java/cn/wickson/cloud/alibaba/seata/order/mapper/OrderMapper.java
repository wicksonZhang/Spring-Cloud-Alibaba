package cn.wickson.cloud.alibaba.seata.order.mapper;

import cn.wickson.cloud.alibaba.seata.order.model.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
