package cn.wickson.cloud.alibaba.sentinel.handler;

import cn.wickson.cloud.alibaba.enums.ResultCodeEnum;
import cn.wickson.cloud.alibaba.utils.ResultUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;

/**
 * sentinel限流、降级和熔断全局异常处理类
 */
public class SentinelExceptionHandler {

    public static ResultUtil handleException(BlockException exception) {
        ResultUtil resultUtil = new ResultUtil();
        try {
            if (exception instanceof FlowException) {
                resultUtil = ResultUtil.failure(ResultCodeEnum.SENTINEL_INTERFACE_CURRENT_LIMIT);
            }
            if (exception instanceof DegradeException) {
                resultUtil = ResultUtil.failure(ResultCodeEnum.SENTINEL_SERVICE_DOWNGRADE);
            }
            if (exception instanceof ParamFlowException) {
                resultUtil = ResultUtil.failure(ResultCodeEnum.SENTINEL_HOTSPOT_PARAMETER_CURRENT_LIMIT);
            }
            if (exception instanceof SystemBlockException) {
                resultUtil = ResultUtil.failure(ResultCodeEnum.SENTINEL_TRIGGER_SYSTEM_PROTECTION_RULES);
            }
            if (exception instanceof AuthorityException) {
                resultUtil = ResultUtil.failure(ResultCodeEnum.SENTINEL_AUTHORIZATION_RULES_FAILED);
            }
        } catch (Exception e) {
            // 处理异常，可以记录日志或者返回特定的错误信息
            return ResultUtil.failure(ResultCodeEnum.SENTINEL_INTERFACE_CURRENT_LIMIT);
        }
        return resultUtil;
    }

}
