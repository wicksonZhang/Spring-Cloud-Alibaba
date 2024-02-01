package cn.wickson.cloud.alibaba.seata.business.app.service;

import cn.hutool.core.util.ObjectUtil;
import cn.wickson.cloud.alibaba.enums.ResultCodeEnum;
import cn.wickson.cloud.alibaba.exception.TripartiteInterfaceException;
import cn.wickson.cloud.alibaba.model.dto.OrderDTO;
import cn.wickson.cloud.alibaba.model.dto.StockDTO;
import cn.wickson.cloud.alibaba.seata.business.feign.ApiOrderFeign;
import cn.wickson.cloud.alibaba.seata.business.feign.ApiStockFeign;
import cn.wickson.cloud.alibaba.utils.ResultUtil;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 业务类-应用服务抽象类(防腐层)
 *
 * @author ZhangZiHeng
 * @date 2024-02-01
 */
public abstract class AbstractBusinessAppService {

    @Resource
    private ApiOrderFeign orderFeign;

    @Resource
    private ApiStockFeign stockFeign;

    /**
     * 减少库存
     *
     * @param stockDTO
     */
    protected void delStock(StockDTO stockDTO) {
        ResultUtil resultUtil = stockFeign.deduct(stockDTO);
        if (ObjectUtil.isNull(resultUtil.getCode())) {
            throw TripartiteInterfaceException.getInstance(ResultCodeEnum.SEATA_STOCK_RPC_ERROR);
        }
        if (resultUtil.getCode() != ResultCodeEnum.SUCCESS.getCode()) {
            throw TripartiteInterfaceException.getInstance(resultUtil.getCode(), resultUtil.getMessage());
        }
    }

    /**
     * 创建订单
     *
     * @param orderDTO
     */
    protected void createOrder(OrderDTO orderDTO) {
        BigDecimal orderAmount = orderDTO.getPrice().multiply(BigDecimal.valueOf(orderDTO.getOrderCount()));
        orderDTO.setOrderAmount(orderAmount);
        ResultUtil resultUtil = orderFeign.create(orderDTO);
        if (ObjectUtil.isNull(resultUtil.getCode())) {
            throw TripartiteInterfaceException.getInstance(ResultCodeEnum.SEATA_ORDER_RPC_ERROR);
        }
        if (resultUtil.getCode() != ResultCodeEnum.SUCCESS.getCode()) {
            throw TripartiteInterfaceException.getInstance(resultUtil.getCode(), resultUtil.getMessage());
        }
    }
}
