package cn.wickson.cloud.alibaba.seata.storage.app.service.impl;

import cn.wickson.cloud.alibaba.model.dto.StockDTO;
import cn.wickson.cloud.alibaba.seata.storage.app.service.AbstractStockAppService;
import cn.wickson.cloud.alibaba.seata.storage.app.service.IStockService;
import cn.wickson.cloud.alibaba.seata.storage.convert.StockConvert;
import cn.wickson.cloud.alibaba.seata.storage.model.entity.Stock;
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
public class StockServiceImpl extends AbstractStockAppService implements IStockService {

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public StockDTO deduct(StockDTO stockDTO) {
        /* Step-1: 参数校验 */
        ParamFormatUtil.formatParam(stockDTO);

        /* Step-2: Stock validated */
        Stock stock = this.validateUpdateParam(stockDTO);

        /* Step-3: 减少库存 */
        stock.setCount(stock.getCount() - stockDTO.getCount());
        stockRepository.updateById(stock);

        return StockConvert.INSTANCE.toDTO(stock);
    }

    @Override
    public List<StockDTO> listAllByStock() {
        List<Stock> stockList = stockRepository.list();
        return StockConvert.INSTANCE.toStockDTO(stockList);
    }

}
