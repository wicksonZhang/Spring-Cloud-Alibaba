package cn.wickson.alibaba.cloud.seata.storage.convert;

import cn.wickson.alibaba.cloud.seata.storage.model.entity.Stock;
import cn.wickson.cloud.alibaba.model.dto.StockDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@Mapper
public interface StockConvert {

    StockConvert INSTANCE = Mappers.getMapper(StockConvert.class);

    /**
     * DO to DTO
     *
     * @param stock 库存
     * @return StockDTO
     */
    StockDTO toDTO(Stock stock);
}
