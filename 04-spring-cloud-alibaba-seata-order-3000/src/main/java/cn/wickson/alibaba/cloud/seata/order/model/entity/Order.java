package cn.wickson.alibaba.cloud.seata.order.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String orderNo;

    private String userId;

    private String commodityCode;

    private Integer count;

    private BigDecimal amount;

}
