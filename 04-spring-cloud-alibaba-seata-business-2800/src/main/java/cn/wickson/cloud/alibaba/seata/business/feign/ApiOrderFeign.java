package cn.wickson.cloud.alibaba.seata.business.feign;

import cn.wickson.cloud.alibaba.model.dto.OrderDTO;
import cn.wickson.cloud.alibaba.seata.business.feign.fallback.OrderFallbackFactory;
import cn.wickson.cloud.alibaba.utils.ResultUtil;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@Component
@FeignClient(value = "spring-cloud-alibaba-seata-order", fallbackFactory = OrderFallbackFactory.class)
public interface ApiOrderFeign {

    /**
     * 创建订单
     *
     * @param orderDTO
     */
    @PostMapping("/order/create")
    ResultUtil create(@RequestBody OrderDTO orderDTO);
}
