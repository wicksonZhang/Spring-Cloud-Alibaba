package cn.wickson.cloud.alibaba.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {

    /**
     * 商品 Code
     */
    private String commodityCode;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品数量
     */
    private Integer count;

    public static StockDTO getInstance(){
        return new StockDTO();
    }

}
