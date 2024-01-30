package cn.wickson.cloud.alibaba.sentinel.consumer.ribbon.controller;

import cn.wickson.cloud.alibaba.enums.ResultCodeEnum;
import cn.wickson.cloud.alibaba.utils.ResultUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 消费者-控制类
 *
 * @author ZhangZiHeng
 * @date 2024-01-29
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/ribbon/consumer")
public class RibbonConsumerController {

    @Value("${service-url.sentinel-producer-service}")
    private String serviceUrl;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/fallback/{id}")
    // fallback 降级时调用
//    @SentinelResource(value = "fallback",fallback = "handlerFallback")
    // fallback: 降级时调用; blockHandler: 流控时调用;
    @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler")
    // fallback: 降级时调用; blockHandler: 流控时调用; exceptionsToIgnore: 忽略异常
//    @SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "blockHandler", exceptionsToIgnore = {IllegalArgumentException.class})
    public ResultUtil fallback(@PathVariable("id") Long id) {
        ResultUtil result = restTemplate.getForObject(serviceUrl + "/producer/" + id, ResultUtil.class);
        if (result == null) {
            throw new NullPointerException("Id is not found");
        }
        if (null == result.getData()) {
            throw new IllegalArgumentException("param is not found");
        }
        return result;
    }

    public ResultUtil handlerFallback(Long id, Throwable throwable) {
        return ResultUtil.failure(ResultCodeEnum.SYSTEM_ERROR, "id: " + id + " ,fallback: 降级时调用, " + throwable.getMessage());
    }

    public ResultUtil blockHandler(Long id, BlockException blockException) {
        return ResultUtil.failure(ResultCodeEnum.SYSTEM_ERROR, "id: " + id + " ,blockHandler: 流控时调用, " + blockException.getMessage());
    }

}
