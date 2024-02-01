package cn.wickson.cloud.alibaba.seata.storage.controller;

import cn.wickson.cloud.alibaba.seata.storage.service.IStockService;
import cn.wickson.cloud.alibaba.model.dto.StockDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/stock")
public class StockController {

    @Resource
    private IStockService stockService;

    /**
     * 减少库存
     *
     * @param stockDTO
     */
    @PutMapping(value = "/deduct")
    public StockDTO deduct(@RequestBody StockDTO stockDTO) {
        return stockService.deduct(stockDTO);
    }

}
