package cn.wickson.cloud.alibaba.seata.business.app.service;

import cn.wickson.cloud.alibaba.model.vo.BusinessVO;

/**
 * @author ZhangZiHeng
 * @date 2024-01-31
 */
public interface IBusinessAppService {

    /**
     * 采购
     *
     * @param businessVO
     */
    void purchase(BusinessVO businessVO);

}
