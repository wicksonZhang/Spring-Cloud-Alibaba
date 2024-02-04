package cn.wickson.cloud.alibaba.handle;

import cn.wickson.cloud.alibaba.enums.ResultCodeEnum;
import cn.wickson.cloud.alibaba.exception.UserOperationException;
import cn.wickson.cloud.alibaba.model.dto.OrderDTO;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;

/**
 * sentinel限流、降级和熔断全局异常处理类
 */
public class GlobalSentinelExceptionHandler {

    /**
     * 创建订单时进行限流
     *
     * @param orderDTO
     * @param exception
     */
    public static void handleOrderCreateException(OrderDTO orderDTO, BlockException exception) {
        handleException(exception);
    }

    private static void handleException(BlockException exception) {
        try {
            if (exception instanceof FlowException) {
                throw UserOperationException.getInstance(ResultCodeEnum.SENTINEL_INTERFACE_CURRENT_LIMIT);
            }
            if (exception instanceof DegradeException) {
                throw UserOperationException.getInstance(ResultCodeEnum.SENTINEL_SERVICE_DOWNGRADE);
            }
            if (exception instanceof ParamFlowException) {
                throw UserOperationException.getInstance(ResultCodeEnum.SENTINEL_HOTSPOT_PARAMETER_CURRENT_LIMIT);
            }
            if (exception instanceof SystemBlockException) {
                throw UserOperationException.getInstance(ResultCodeEnum.SENTINEL_TRIGGER_SYSTEM_PROTECTION_RULES);
            }
            if (exception instanceof AuthorityException) {
                throw UserOperationException.getInstance(ResultCodeEnum.SENTINEL_AUTHORIZATION_RULES_FAILED);
            }
        } catch (Exception e) {
            // 处理异常，可以记录日志或者返回特定的错误信息
            throw UserOperationException.getInstance(ResultCodeEnum.SENTINEL_INTERFACE_CURRENT_LIMIT);
        }
    }

}
