package cn.wickson.cloud.alibaba.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @NotBlank(message = "商品编号不能为空")
    private String commodityCode;

    /**
     * 商品名称
     */
    @NotBlank(message = "商品名称不能为空")
    private String name;

    /**
     * 商品数量
     */
    @NotNull(message = "商品数量不能为空")
    @Min(value = 1, message = "商品数量不能小于1")
    @Max(value = 999, message = "商品数量不能超过999")
    private Integer count;

    public static StockDTO getInstance(){
        return new StockDTO();
    }

}
