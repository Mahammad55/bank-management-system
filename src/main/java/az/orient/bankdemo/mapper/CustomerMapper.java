package az.orient.bankdemo.mapper;

import az.orient.bankdemo.dto.request.ReqCustomer;
import az.orient.bankdemo.dto.response.RespCustomer;
import az.orient.bankdemo.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(target = "customerId",source = "id")
    RespCustomer toResponse(Customer customer);

    Customer toEntity(ReqCustomer reqCustomer);
}
