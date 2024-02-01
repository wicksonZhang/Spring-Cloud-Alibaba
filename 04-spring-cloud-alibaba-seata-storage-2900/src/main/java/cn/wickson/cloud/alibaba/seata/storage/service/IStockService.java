package cn.wickson.cloud.alibaba.seata.storage.service;

import cn.wickson.cloud.alibaba.model.dto.StockDTO;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
public interface IStockService {

    /**
     * 减少库存
     *
     * @param stockDTO
     */
    StockDTO deduct(StockDTO stockDTO);

}
