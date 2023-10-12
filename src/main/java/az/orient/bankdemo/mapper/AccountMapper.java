package az.orient.bankdemo.mapper;

import az.orient.bankdemo.dto.request.ReqAccount;
import az.orient.bankdemo.dto.response.RespAccount;
import az.orient.bankdemo.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(target = "respCustomer",source = "customer")
    @Mapping(target = "respCustomer.customerId",source = "customer.id")
    RespAccount toResponse(Account account);

    @Mapping(target = "customer.id",source = "customerId")
    Account toEntity(ReqAccount reqAccount);
}
