package cn.wickson.alibaba.cloud.seata.storage.repository.impl;

import cn.wickson.alibaba.cloud.seata.storage.mapper.StockMapper;
import cn.wickson.alibaba.cloud.seata.storage.model.entity.Stock;
import cn.wickson.alibaba.cloud.seata.storage.repository.IStockRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@Service
public class StockRepositoryImpl extends ServiceImpl<StockMapper, Stock> implements IStockRepository {
}
