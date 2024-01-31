package cn.wickson.alibaba.cloud.seata.storage.service.impl;

import cn.hutool.core.util.ObjUtil;
import cn.wickson.alibaba.cloud.seata.storage.convert.StockConvert;
import cn.wickson.alibaba.cloud.seata.storage.model.entity.Stock;
import cn.wickson.alibaba.cloud.seata.storage.repository.IStockRepository;
import cn.wickson.alibaba.cloud.seata.storage.service.IStockService;
import cn.wickson.cloud.alibaba.enums.ResultCodeEnum;
import cn.wickson.cloud.alibaba.exception.UserOperationException;
import cn.wickson.cloud.alibaba.model.dto.StockDTO;
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
    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public StockDTO deduct(String commodityCode, int orderCount) {
        Stock stock = stockRepository.lambdaQuery()
                .eq(ObjUtil.isNotNull(commodityCode), Stock::getCommodityCode, commodityCode)
                .one();
        if (ObjUtil.isNull(stock)) {
            throw UserOperationException.getInstance(ResultCodeEnum.SEATA_STOCK_CODE_NULL_POINT_EXCEPTION);
        }
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
