package cn.wickson.cloud.alibaba.seata.storage.app.service;

import cn.wickson.cloud.alibaba.model.dto.StockDTO;

import java.util.List;

public interface IStockService {

    /**
     * 减少库存
     *
     * @param stockDTO
     */
    StockDTO deduct(StockDTO stockDTO);

    List<StockDTO> listAllByStock();

}
