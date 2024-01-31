package cn.wickson.alibaba.cloud.seata.business.service.impl;

import cn.wickson.alibaba.cloud.seata.business.feign.ApiOrderFeign;
import cn.wickson.alibaba.cloud.seata.business.feign.ApiStockFeign;
import cn.wickson.alibaba.cloud.seata.business.service.IBusinessService;
import cn.wickson.cloud.alibaba.model.dto.BusinessDTO;
import cn.wickson.cloud.alibaba.model.dto.OrderDTO;
import cn.wickson.cloud.alibaba.model.dto.StockDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@Service
public class BusinessServiceImpl implements IBusinessService {

    @Resource
    private ApiOrderFeign orderFeign;

    @Resource
    private ApiStockFeign stockFeign;

    @Override
    public void purchase(final BusinessDTO businessDTO) {
        //1、减少库存
        StockDTO stockDTO = new StockDTO();
        stockDTO.setCommodityCode(businessDTO.getCommodityCode());
        stockDTO.setCount(businessDTO.getCount());
        stockDTO.setName(businessDTO.getName());
        stockFeign.deduct(stockDTO);

        //2、创建订单
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserId(businessDTO.getUserId());
        orderDTO.setCommodityCode(businessDTO.getCommodityCode());
        orderDTO.setOrderCount(businessDTO.getCount());
        orderDTO.setOrderAmount(businessDTO.getAmount());
        orderFeign.create(orderDTO);
    }

}
