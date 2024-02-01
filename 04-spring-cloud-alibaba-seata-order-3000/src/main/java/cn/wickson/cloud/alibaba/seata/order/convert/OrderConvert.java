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
    @Mappings(
            @Mapping(target = "amount", source = "orderDTO.orderAmount")
    )
    AccountDTO toAccountDTO(OrderDTO orderDTO);

    /**
     * OrderDTO To Order
     *
     * @param orderDTO
     * @return
     */
    Order toOrderDO(OrderDTO orderDTO);
}
