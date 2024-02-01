package cn.wickson.cloud.alibaba.seata.business.feign;

import cn.wickson.cloud.alibaba.model.dto.StockDTO;
import cn.wickson.cloud.alibaba.seata.business.feign.fallback.StockFallbackFactory;
import cn.wickson.cloud.alibaba.utils.ResultUtil;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@Component
@FeignClient(value = "spring-cloud-alibaba-seata-stock", fallbackFactory = StockFallbackFactory.class)
public interface ApiStockFeign {

    /**
     * 扣除存储数量
     */
    @PutMapping(value = "/stock/deduct")
    ResultUtil deduct(@RequestBody StockDTO stockDTO);

}
