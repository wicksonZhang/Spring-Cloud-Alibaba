package cn.wickson.alibaba.cloud.seata.order.feign;

import cn.wickson.alibaba.cloud.seata.order.feign.fallback.AccountFeignFallback;
import cn.wickson.cloud.alibaba.model.dto.AccountDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@Component
@FeignClient(value = "spring-cloud-alibaba-seata-account", fallback = AccountFeignFallback.class)
public interface ApiAccountFeign {


    void debit(AccountDTO accountDTO);

}
