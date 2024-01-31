package cn.wickson.cloud.alibaba.model.dto;

import cn.wickson.cloud.alibaba.utils.ResultUnpacked;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO implements ResultUnpacked {

    private String orderNo;

    private String userId;

    private String commodityCode;

    private Integer orderCount;

    private BigDecimal orderAmount;

}
