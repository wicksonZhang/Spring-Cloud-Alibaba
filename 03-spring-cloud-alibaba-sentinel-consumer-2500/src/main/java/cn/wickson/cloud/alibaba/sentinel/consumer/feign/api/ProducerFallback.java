package cn.wickson.cloud.alibaba.sentinel.consumer.feign.api;

import cn.wickson.cloud.alibaba.enums.ResultCodeEnum;
import cn.wickson.cloud.alibaba.utils.ResultUtil;
import org.springframework.stereotype.Component;

/**
 * @author ZhangZiHeng
 * @date 2024-01-30
 */
@Component
public class ProducerFallback implements ApiProducerFeign {

    @Override
    public ResultUtil producer(Long id) {
        return ResultUtil.failure(ResultCodeEnum.SYSTEM_ERROR, "id: " + id + " ,fallback: 降级时调用");
    }

}
