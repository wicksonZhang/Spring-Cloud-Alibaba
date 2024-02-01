package cn.wickson.cloud.alibaba.seata.storage.service.impl;

import cn.hutool.core.util.ObjUtil;
import cn.wickson.cloud.alibaba.seata.storage.convert.StockConvert;
import cn.wickson.cloud.alibaba.seata.storage.model.entity.Stock;
import cn.wickson.cloud.alibaba.seata.storage.repository.IStockRepository;
import cn.wickson.cloud.alibaba.seata.storage.service.IStockService;
import cn.wickson.cloud.alibaba.enums.ResultCodeEnum;
import cn.wickson.cloud.alibaba.exception.UserOperationException;
import cn.wickson.cloud.alibaba.model.dto.StockDTO;
import cn.wickson.cloud.alibaba.utils.ResultUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@Service
public class StockServiceImpl implements IStockService {

    @Resource
    private IStockRepository stockRepository;

    @Override
    public StockDTO deduct(StockDTO stockDTO) {
        Stock stock = stockRepository.lambdaQuery()
                .eq(ObjUtil.isNotNull(stockDTO.getCommodityCode()), Stock::getCommodityCode, stockDTO.getCommodityCode())
                .one();
        if (ObjUtil.isNull(stock)) {
            throw UserOperationException.getInstance(ResultCodeEnum.SEATA_STOCK_CODE_NULL_POINT_EXCEPTION);
        }
        Integer orderCount = stockDTO.getCount();
        if (orderCount > stock.getCount()) {
            throw UserOperationException.getInstance(ResultCodeEnum.SEATA_STOCK_INVENTORY_SHORTAGE);
        }
        int orderTotal = stock.getCount() - orderCount;
        stock.setCount(orderTotal);
        boolean flag = stockRepository.updateById(stock);
        if (!flag) {
            throw UserOperationException.getInstance(ResultCodeEnum.SEATA_STOCK_UPDATE_ERROR);
        }
        return StockConvert.INSTANCE.toDTO(stock);
    }
}
