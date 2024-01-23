package cn.wickson.cloud.alibaba.producer1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 生产者控制类
 *
 * @author ZhangZiHeng
 * @date 2024-01-23
 */
@Slf4j
@Validated
@RestController
@RefreshScope // 支持 Nacos 动态刷新配置
@RequestMapping("/producer")
public class Producer1Controller {

    @Value("${server.port}")
    private Integer serverPort;

    @Value("${config.info}")
    private String configInfo;

    @GetMapping(value = "/nacos")
    public String getProducerConfig() {
        return "The producer1 server.port: " + serverPort + " registered to nacos.The producer1 config：" + configInfo;
    }

}
