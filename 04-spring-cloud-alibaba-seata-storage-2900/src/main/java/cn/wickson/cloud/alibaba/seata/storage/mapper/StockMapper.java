package cn.wickson.cloud.alibaba.seata.storage.mapper;

import cn.wickson.cloud.alibaba.seata.storage.model.entity.Stock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@Mapper
public interface StockMapper extends BaseMapper<Stock> {
}
