package cn.wickson.cloud.alibaba.model.vo;

import cn.wickson.cloud.alibaba.utils.ResultUnpacked;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@Data
public class BusinessVO implements ResultUnpacked {

    @NotNull(message = "用户Id不能为空")
    private Long userId;

    @NotBlank(message = "商品Code不能为空")
    private String commodityCode;

    @NotBlank(message = "商品名称不能为空")
    private String name;

    @NotNull(message = "商品数量不能为空")
    @Min(value = 0, message = "商品数量不能小于 0")
    @Max(value = 999, message = "商品数量不能大于 999")
    private Integer count;

    @NotNull(message = "商品价格不能为空")
    @Min(value = 0, message = "商品价格不能小于 0")
    @Max(value = 999999999999L, message = "商品价格不能大于 999999999999")
    private BigDecimal price;

}
