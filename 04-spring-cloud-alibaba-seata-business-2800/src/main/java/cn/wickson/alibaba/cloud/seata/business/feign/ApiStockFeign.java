package cn.wickson.alibaba.cloud.seata.business.feign;

import cn.wickson.alibaba.cloud.seata.business.feign.fallback.StockFallback;
import cn.wickson.cloud.alibaba.model.dto.StockDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@FeignClient(value = "spring-cloud-alibaba-seata-stock", fallback = StockFallback.class)
public interface ApiStockFeign {

    /**
     * 扣除存储数量
     */
    @PutMapping(value = "/stock/deduct")
    StockDTO deduct(@RequestBody StockDTO stockDTO);
}
