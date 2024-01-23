package cn.wickson.cloud.alibaba.consumer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 消费者控制类
 *
 * @author ZhangZiHeng
 * @date 2024-01-23
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverUrl;

    @GetMapping("/getProducer")
    public String getProducer() {
        return restTemplate.getForObject(serverUrl + "/producer/nacos", String.class);
    }

}
