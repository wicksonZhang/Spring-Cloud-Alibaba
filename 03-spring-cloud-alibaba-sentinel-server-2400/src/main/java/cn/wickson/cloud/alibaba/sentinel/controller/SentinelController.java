package cn.wickson.cloud.alibaba.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

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
    public String service1() throws InterruptedException {
//        TimeUnit.SECONDS.sleep(1);
        return "This is SentinelController.service1()";
    }

    @GetMapping("/service2")
    public String service2() {
        return "This is SentinelController.service2()";
    }

    @GetMapping("/service3")
    public String service3() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return "This is SentinelController.service3() Slow Request Ratio";
    }

    @GetMapping("/service4")
    public String service4() {
        throw new ArithmeticException("/ by zero");
    }

    @GetMapping("/service5")
    @SentinelResource(value = "hotKey", blockHandler = "dealHotKey")
    public String service5(@RequestParam(value = "param1",required = false) String param1) {
        return "This is SentinelController.service5() Hot Key";
    }

    public String dealHotKey(String param1, BlockException exception) {
        return "This is SentinelController.service5() dealHotKey: Error";
    }

}
