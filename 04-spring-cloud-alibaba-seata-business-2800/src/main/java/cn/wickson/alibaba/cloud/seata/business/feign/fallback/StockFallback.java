package cn.wickson.alibaba.cloud.seata.business.feign.fallback;

import cn.wickson.alibaba.cloud.seata.business.feign.ApiStockFeign;
import cn.wickson.cloud.alibaba.model.dto.StockDTO;
import org.springframework.stereotype.Component;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@Component
public class StockFallback implements ApiStockFeign {

    @Override
    public StockDTO deduct(String commodityCode, int orderCount) {
        return null;
    }

}
