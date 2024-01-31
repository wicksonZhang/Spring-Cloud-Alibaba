package cn.wickson.cloud.alibaba.model.dto;

import cn.wickson.cloud.alibaba.utils.ResultUnpacked;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@Data
public class BusinessDTO implements ResultUnpacked {

    private String userId;

    private String commodityCode;

    private String name;

    private Integer count;

    private BigDecimal amount;

}
