package cn.wickson.cloud.alibaba.seata.order.feign.fallback;

import cn.wickson.cloud.alibaba.enums.ResultCodeEnum;
import cn.wickson.cloud.alibaba.model.dto.AccountDTO;
import cn.wickson.cloud.alibaba.seata.order.feign.ApiAccountFeign;
import cn.wickson.cloud.alibaba.utils.ResultUtil;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@Component
public class AccountFeignFallbackFactory implements FallbackFactory<ApiAccountFeign> {

    @Override
    public ApiAccountFeign create(Throwable cause) {
        return new ApiAccountFeign() {
            @Override
            public ResultUtil debit(AccountDTO accountDTO) {
                return ResultUtil.failure(ResultCodeEnum.SEATA_STOCK_RPC_ERROR, cause.getMessage());
            }
        };
    }
}
