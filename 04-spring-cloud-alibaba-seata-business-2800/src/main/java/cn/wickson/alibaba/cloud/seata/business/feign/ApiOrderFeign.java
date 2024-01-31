package cn.wickson.alibaba.cloud.seata.business.feign;

import cn.wickson.alibaba.cloud.seata.business.feign.fallback.OrderFallback;
import cn.wickson.cloud.alibaba.model.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@Component
@FeignClient(value = "spring-cloud-alibaba-seata-order", fallback = OrderFallback.class)
public interface ApiOrderFeign {

    /**
     * 创建订单
     *
     * @param orderDTO
     */
    @PostMapping("/order/create")
    void create(OrderDTO orderDTO);
}
