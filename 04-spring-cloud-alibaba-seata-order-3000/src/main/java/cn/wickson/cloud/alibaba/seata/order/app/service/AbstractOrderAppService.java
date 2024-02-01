package cn.wickson.cloud.alibaba.seata.order.app.service;

import cn.hutool.core.util.ObjectUtil;
import cn.wickson.cloud.alibaba.enums.ResultCodeEnum;
import cn.wickson.cloud.alibaba.exception.TripartiteInterfaceException;
import cn.wickson.cloud.alibaba.model.dto.AccountDTO;
import cn.wickson.cloud.alibaba.seata.order.feign.ApiAccountFeign;
import cn.wickson.cloud.alibaba.seata.order.repository.IOrderRepository;
import cn.wickson.cloud.alibaba.utils.ResultUtil;

import javax.annotation.Resource;

/**
 * @author ZhangZiHeng
 * @date 2024-02-01
 */
public abstract class AbstractOrderAppService {

    @Resource
    protected IOrderRepository orderRepository;

    @Resource
    protected ApiAccountFeign accountFeign;

    protected void delAccount(final AccountDTO accountDTO) {
        ResultUtil resultUtil = accountFeign.debit(accountDTO);
        if (ObjectUtil.isNull(resultUtil.getCode())) {
            throw TripartiteInterfaceException.getInstance(ResultCodeEnum.SEATA_ACCOUNT_RPC_ERROR);
        }
        if (resultUtil.getCode() != ResultCodeEnum.SUCCESS.getCode()) {
            throw TripartiteInterfaceException.getInstance(resultUtil.getCode(), resultUtil.getMessage());
        }
    }
}
