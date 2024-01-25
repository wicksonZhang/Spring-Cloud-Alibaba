package cn.wickson.cloud.alibaba.sentinel.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * sentinel 控制类
 *
 * @author ZhangZiHeng
 * @date 2024-01-25
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/sentinel")
public class SentinelController {

    @GetMapping("/service1")
    public String service1() {
        return "This is SentinelController.service1()";
    }

    @GetMapping("/service2")
    public String service2() {
        return "This is SentinelController.service2()";
    }

}
