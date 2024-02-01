package cn.wickson.cloud.alibaba.seata.business.feign.fallback;

import cn.wickson.cloud.alibaba.enums.ResultCodeEnum;
import cn.wickson.cloud.alibaba.exception.TripartiteInterfaceException;
import cn.wickson.cloud.alibaba.model.dto.StockDTO;
import cn.wickson.cloud.alibaba.seata.business.feign.ApiStockFeign;
import cn.wickson.cloud.alibaba.utils.ResultUtil;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@Component
public class StockFallbackFactory implements FallbackFactory<ApiStockFeign> {

    @Override
    public ApiStockFeign create(Throwable cause) {
        return new ApiStockFeign() {
            @Override
            public ResultUtil deduct(StockDTO stockDTO) {
                return ResultUtil.failure(ResultCodeEnum.SEATA_STOCK_RPC_ERROR);
            }
        };
    }
}
