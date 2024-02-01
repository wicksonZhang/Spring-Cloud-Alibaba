package cn.wickson.cloud.alibaba.seata.business.convert;

import cn.wickson.cloud.alibaba.model.dto.OrderDTO;
import cn.wickson.cloud.alibaba.model.dto.StockDTO;
import cn.wickson.cloud.alibaba.model.vo.BusinessVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author ZhangZiHeng
 * @date 2024-02-01
 */
@Mapper
public interface BusinessConvert {

    BusinessConvert INSTANCE = Mappers.getMapper(BusinessConvert.class);

    /**
     * BusinessVO To StockDTO
     *
     * @param businessVO
     * @return
     */
    StockDTO toStockDTO(BusinessVO businessVO);

    /**
     * BusinessVO To OrderDTO
     *
     * @param businessVO
     * @return
     */
    @Mappings({
            @Mapping(target = "orderCount", source = "businessVO.count")
    })
    OrderDTO toOrderDTO(BusinessVO businessVO);
}
