package cn.wickson.cloud.alibaba.seata.order.feign;

import cn.wickson.cloud.alibaba.model.dto.AccountDTO;
import cn.wickson.cloud.alibaba.seata.order.feign.fallback.AccountFeignFallbackFactory;
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
@FeignClient(value = "spring-cloud-alibaba-seata-account", fallbackFactory = AccountFeignFallbackFactory.class)
public interface ApiAccountFeign {

    /**
     * 扣减账户余额
     *
     * @param accountDTO
     */
    @PutMapping(value = "/account/debit")
    ResultUtil debit(@RequestBody AccountDTO accountDTO);

}
