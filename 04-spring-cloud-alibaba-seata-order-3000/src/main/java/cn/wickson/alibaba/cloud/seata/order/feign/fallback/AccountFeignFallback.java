package cn.wickson.alibaba.cloud.seata.order.feign.fallback;

import cn.wickson.alibaba.cloud.seata.order.feign.ApiAccountFeign;
import cn.wickson.cloud.alibaba.model.dto.AccountDTO;
import org.springframework.stereotype.Component;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@Component
public class AccountFeignFallback implements ApiAccountFeign {
    @Override
    public void debit(AccountDTO accountDTO) {

    }
}
