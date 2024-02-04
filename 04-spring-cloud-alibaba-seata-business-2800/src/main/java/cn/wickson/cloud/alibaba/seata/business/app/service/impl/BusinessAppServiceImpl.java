package cn.wickson.cloud.alibaba.seata.business.app.service.impl;

import cn.wickson.cloud.alibaba.model.dto.OrderDTO;
import cn.wickson.cloud.alibaba.model.dto.StockDTO;
import cn.wickson.cloud.alibaba.model.vo.BusinessVO;
import cn.wickson.cloud.alibaba.seata.business.app.service.AbstractBusinessAppService;
import cn.wickson.cloud.alibaba.seata.business.app.service.IBusinessAppService;
import cn.wickson.cloud.alibaba.seata.business.convert.BusinessConvert;
import cn.wickson.cloud.alibaba.utils.ParamFormatUtil;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
@Service
public class BusinessAppServiceImpl extends AbstractBusinessAppService implements IBusinessAppService {

    /**
     * 采购
     *
     * @param businessVO
     */
    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public void purchase(final BusinessVO businessVO) {
        /* Step-1: 参数校验 */
        ParamFormatUtil.formatParam(businessVO);

        /* Step-2: 减少库存，调用库存信息 */
        StockDTO stockDTO = BusinessConvert.INSTANCE.toStockDTO(businessVO);
        this.delStock(stockDTO);

        /* Step-3、创建订单 */
        OrderDTO orderDTO = BusinessConvert.INSTANCE.toOrderDTO(businessVO);
        this.createOrder(orderDTO);
    }

}
