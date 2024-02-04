package cn.wickson.cloud.alibaba.seata.storage.app.service;

import cn.hutool.core.util.ObjUtil;
import cn.wickson.cloud.alibaba.enums.ResultCodeEnum;
import cn.wickson.cloud.alibaba.exception.UserOperationException;
import cn.wickson.cloud.alibaba.model.dto.StockDTO;
import cn.wickson.cloud.alibaba.seata.storage.model.entity.Stock;
import cn.wickson.cloud.alibaba.seata.storage.repository.IStockRepository;

import javax.annotation.Resource;

/**
 * @author ZhangZiHeng
 * @date 2024-02-04
 */
public abstract class AbstractStockAppService {

    @Resource
    protected IStockRepository stockRepository;

    protected Stock validateUpdateParam(final StockDTO stockDTO) {
        // 验证 CommodityCode
        Stock stock = this.validCommodityCode(stockDTO.getCommodityCode());
        // 验证 商品名称
        this.validName(stock.getName(), stockDTO.getName());
        // 验证 商品库存信息
        this.validCount(stock.getCount(), stockDTO.getCount());
        return stock;
    }

    private void validCount(Integer count, Integer delCount) {
        if (count < delCount) {
            throw UserOperationException.getInstance(ResultCodeEnum.SEATA_STOCK_INVENTORY_SHORTAGE);
        }
    }

    private void validName(String oldName, String newName) {
        if (!newName.equals(oldName)) {
            throw UserOperationException.getInstance(ResultCodeEnum.SEATA_STOCK_NAME_DIFFERENT_EXCEPTION);
        }
    }

    private Stock validCommodityCode(String commodityCode) {
        Stock stock = stockRepository.lambdaQuery()
                .eq(Stock::getCommodityCode, commodityCode)
                .last("LIMIT 1")
                .one();
        if (ObjUtil.isNull(stock)) {
            throw UserOperationException.getInstance(ResultCodeEnum.SEATA_STOCK_CODE_NULL_POINT_EXCEPTION);
        }
        return stock;
    }
}
