package cn.wickson.alibaba.cloud.seata.storage.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_stock")
public class Stock implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String commodityCode;

    private String name;

    private Integer count;

}
