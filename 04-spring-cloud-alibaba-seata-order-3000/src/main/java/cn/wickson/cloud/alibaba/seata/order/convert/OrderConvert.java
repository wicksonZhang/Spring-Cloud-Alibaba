package cn.wickson.cloud.alibaba.seata.order.convert;

import cn.wickson.cloud.alibaba.model.dto.AccountDTO;
import cn.wickson.cloud.alibaba.model.dto.OrderDTO;
import cn.wickson.cloud.alibaba.model.dto.StockDTO;
import cn.wickson.cloud.alibaba.model.vo.BusinessVO;
import cn.wickson.cloud.alibaba.seata.order.model.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author ZhangZiHeng
 * @date 2024-02-01
 */
@Mapper
public interface OrderConvert {

    OrderConvert INSTANCE = Mappers.getMapper(OrderConvert.class);

    /**
     * OrderDTO To AccountDTO
     *
     * @param orderDTO
     * @return
     */
    @Mappings({
            @Mapping(target = "amount", source = "orderDTO.orderAmount")
    })
    AccountDTO toAccountDTO(OrderDTO orderDTO);

    /**
     * OrderDTO To Order
     *
     * @param orderDTO
     * @return
     */
    @Mapping(target = "amount", source = "orderDTO.orderAmount")
    @Mapping(target = "count", source = "orderDTO.orderCount")
    Order toOrderDO(OrderDTO orderDTO);

    @Mapping(target = "orderAmount", source = "order.amount")
    @Mapping(target = "orderCount", source = "order.count")
    OrderDTO toOrderDTO(Order order);

    @Mapping(target = "orderAmount", source = "order.amount")
    @Mapping(target = "orderCount", source = "order.count")
    List<OrderDTO> toOrderDTOList(List<Order> orderList);

}
