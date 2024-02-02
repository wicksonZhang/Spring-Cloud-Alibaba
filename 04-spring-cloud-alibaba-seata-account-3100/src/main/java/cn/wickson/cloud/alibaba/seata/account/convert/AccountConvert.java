package cn.wickson.cloud.alibaba.seata.account.convert;

import cn.wickson.cloud.alibaba.model.dto.AccountDTO;
import cn.wickson.cloud.alibaba.seata.account.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author ZhangZiHeng
 * @date 2024-02-02
 */
@Mapper
public interface AccountConvert {

    AccountConvert INSTANCE = Mappers.getMapper(AccountConvert.class);

    List<AccountDTO> toDTO(List<Account> accountList);
}
