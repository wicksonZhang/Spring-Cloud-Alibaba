package cn.wickson.cloud.alibaba.sentinel.controller;

import cn.wickson.cloud.alibaba.sentinel.handler.SentinelExceptionHandler;
import cn.wickson.cloud.alibaba.utils.ResultUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 自定义限流
 *
 * @author ZhangZiHeng
 * @date 2024-01-29
 */
@RestController
@RequestMapping("/sentinel")
public class SentinelCustomerController {

    /**
     * SentinelResource: 用于定义资源，并提供可选的异常处理和 fallback 配置项。
     * * value: 资源名称
     * * blockHandlerClass: 对应的类的 Class 对象
     * * blockHandler: blockHandler 对应处理 BlockException 的函数名称
     */
    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler", blockHandlerClass = SentinelExceptionHandler.class, blockHandler = "handleException")
    public ResultUtil customerBlockHandler() {
        return ResultUtil.success("This is SentinelCustomerController customerBlockHandler()");
    }

}
