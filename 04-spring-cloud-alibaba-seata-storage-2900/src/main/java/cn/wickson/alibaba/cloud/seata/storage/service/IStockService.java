package cn.wickson.alibaba.cloud.seata.storage.service;

import cn.wickson.cloud.alibaba.model.dto.StockDTO;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
public interface IStockService {

    /**
     * 减少库存
     *
     * @param commodityCode
     * @param orderCount
     */
    StockDTO deduct(String commodityCode, int orderCount);

}
