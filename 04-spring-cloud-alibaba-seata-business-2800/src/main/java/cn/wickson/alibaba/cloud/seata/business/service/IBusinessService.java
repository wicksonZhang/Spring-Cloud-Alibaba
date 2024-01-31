package cn.wickson.alibaba.cloud.seata.business.service;

import cn.wickson.cloud.alibaba.model.dto.BusinessDTO;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
public interface IBusinessService {

    /**
     * 采购
     *
     * @param businessDTO
     */
    void purchase(BusinessDTO businessDTO);

}
