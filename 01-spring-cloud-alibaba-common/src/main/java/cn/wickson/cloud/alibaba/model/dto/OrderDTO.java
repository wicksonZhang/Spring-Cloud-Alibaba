package cn.wickson.cloud.alibaba.model.dto;

import cn.wickson.cloud.alibaba.utils.ResultUnpacked;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO implements Serializable {

    private String orderNo;

    private Long userId;

    private String commodityCode;

    @NotNull(message = "订单数量不能为0")
    @Min(value = 1, message = "订单数量最少为1")
    @Max(value = 999, message = "订单数量最多为999")
    private Integer orderCount;

    @NotNull(message = "订单价格不能为0")
    private BigDecimal price;

    private BigDecimal orderAmount;

}
