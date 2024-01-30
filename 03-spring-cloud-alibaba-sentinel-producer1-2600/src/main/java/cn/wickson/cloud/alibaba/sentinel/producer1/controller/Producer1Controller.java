package cn.wickson.cloud.alibaba.sentinel.producer1.controller;

import cn.hutool.core.lang.UUID;
import cn.wickson.cloud.alibaba.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 生产者-控制类
 *
 * @author ZhangZiHeng
 * @date 2024-01-29
 */
@Slf4j
@Validated
@RestController
public class Producer1Controller {

    @GetMapping("/producer/{id}")
    public ResultUtil producer1(@PathVariable("id") Long id) {
        Map<Long, String> map = this.getResult();
        return ResultUtil.success(map.get(id));
    }

    private Map<Long, String> getResult() {
        Map<Long, String> map = new HashMap<>();
        map.put(1L, UUID.randomUUID().toString());
        map.put(2L, UUID.randomUUID().toString());
        map.put(3L, UUID.randomUUID().toString());
        return map;
    }

}
