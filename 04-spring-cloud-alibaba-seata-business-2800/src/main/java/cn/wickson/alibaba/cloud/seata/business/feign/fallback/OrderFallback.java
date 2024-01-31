package cn.wickson.alibaba.cloud.seata.business.feign.fallback;

import cn.wickson.alibaba.cloud.seata.business.feign.ApiOrderFeign;
import org.springframework.stereotype.Component;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@Component
public class OrderFallback implements ApiOrderFeign {

    @Override
    public void create(String userId, String commodityCode, int orderCount) {

    }

}
