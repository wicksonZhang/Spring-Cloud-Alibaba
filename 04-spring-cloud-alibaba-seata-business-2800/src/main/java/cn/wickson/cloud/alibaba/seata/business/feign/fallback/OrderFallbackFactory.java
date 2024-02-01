package cn.wickson.cloud.alibaba.seata.business.feign.fallback;

import cn.wickson.cloud.alibaba.enums.ResultCodeEnum;
import cn.wickson.cloud.alibaba.model.dto.OrderDTO;
import cn.wickson.cloud.alibaba.seata.business.feign.ApiOrderFeign;
import cn.wickson.cloud.alibaba.utils.ResultUtil;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@Component
public class OrderFallbackFactory implements FallbackFactory<ApiOrderFeign> {

    @Override
    public ApiOrderFeign create(Throwable cause) {
        return new ApiOrderFeign() {
            @Override
            public ResultUtil create(OrderDTO orderDTO) {
               return ResultUtil.failure(ResultCodeEnum.SEATA_ORDER_RPC_ERROR);
            }
        };
    }
}
