package cn.wickson.cloud.alibaba.sentinel.consumer.feign.controller;

import cn.wickson.cloud.alibaba.sentinel.consumer.feign.api.ApiProducerFeign;
import cn.wickson.cloud.alibaba.utils.ResultUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ZhangZiHeng
 * @date 2024-01-29
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/feign/consumer")
public class FeignConsumerController {

    @Resource
    private ApiProducerFeign producerFeign;

    @GetMapping("/fallback/{id}")
    public ResultUtil fallback(@PathVariable("id") Long id) {
        return producerFeign.producer(id);
    }

    @GetMapping("/fallback/persistence")
    public ResultUtil persistence() {
        return ResultUtil.success();
    }

}
