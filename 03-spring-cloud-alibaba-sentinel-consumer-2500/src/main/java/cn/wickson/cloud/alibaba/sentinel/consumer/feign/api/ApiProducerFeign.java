package cn.wickson.cloud.alibaba.sentinel.consumer.feign.api;

import cn.wickson.cloud.alibaba.utils.ResultUtil;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author ZhangZiHeng
 * @date 2024-01-30
 */
@Component
@FeignClient(value = "spring-cloud-alibaba-sentinel-producer", fallback = ProducerFallback.class)
public interface ApiProducerFeign {

    @GetMapping("/producer/{id}")
    public ResultUtil producer(@PathVariable("id") Long id);

}
