package cn.wickson.cloud.alibaba.producer2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangZiHeng
 * @date 2024-01-23
 */
@Slf4j
@Validated
@RestController
@RefreshScope // 支持 Nacos 动态刷新配置
@RequestMapping("/producer")
public class Producer2Controller {

    @Value("${server.port}")
    private Integer serverPort;

    @Value("${config.info}")
    private String configInfo;

    @GetMapping(value = "/nacos")
    public String getProducerConfig() {
        return "The producer2 server.port: " + serverPort + " registered to nacos. The producer2 config：" + configInfo;
    }

}
