package az.orient.bankdemo.mapper;

import az.orient.bankdemo.dto.request.ReqTransaction;
import az.orient.bankdemo.dto.response.RespTransaction;
import az.orient.bankdemo.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mapping(target = "respAccount",source = "fromAccount")
    @Mapping(target = "respAccount.respCustomer",source = "fromAccount.customer")
    @Mapping(target = "respAccount.respCustomer.customerId",source = "fromAccount.customer.id")
    RespTransaction toResponse(Transaction transaction);

    @Mapping(target = "fromAccount.id",source = "fromAccountId")
    Transaction toEntity(ReqTransaction  reqTransaction);
}
