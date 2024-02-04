package cn.wickson.cloud.alibaba.seata.storage.controller;

import cn.wickson.cloud.alibaba.model.dto.StockDTO;
import cn.wickson.cloud.alibaba.seata.storage.app.service.IStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@Slf4j
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
    public StockDTO deduct(@RequestBody @Validated StockDTO stockDTO) {
        return stockService.deduct(stockDTO);
    }

    @GetMapping("/list")
    public List<StockDTO> listAll() {
        return stockService.listAllByStock();
    }
}
