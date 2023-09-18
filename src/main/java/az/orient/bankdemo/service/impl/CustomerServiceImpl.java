package az.orient.bankdemo.service.impl;

import az.orient.bankdemo.dto.request.ReqCustomer;
import az.orient.bankdemo.dto.response.RespCustomer;
import az.orient.bankdemo.dto.response.RespStatus;
import az.orient.bankdemo.dto.response.Response;
import az.orient.bankdemo.entity.Customer;
import az.orient.bankdemo.enums.EnumAvailableStatus;
import az.orient.bankdemo.exception.BankException;
import az.orient.bankdemo.exception.ExceptionConstant;
import az.orient.bankdemo.mapper.CustomerMapper;
import az.orient.bankdemo.repository.CustomerRepository;
import az.orient.bankdemo.service.CustomerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository;

    CustomerMapper mapper;

    private static final String CUSTOMER_NOT_FOUND_MESSAGE = "Customer not found!";
    private static final String INVALID_REQUEST_DATA_MESSAGE = "Invalid request data";

    @Override
    public Response<List<RespCustomer>> getCustomerList() {
        Response<List<RespCustomer>> response = new Response<>();
        List<Customer> customerList = customerRepository.findAllByActive(EnumAvailableStatus.ACTIVE.value);
        if (customerList.isEmpty()) {
            throw new BankException(ExceptionConstant.CUSTOMER_NOT_FOUND, CUSTOMER_NOT_FOUND_MESSAGE);
        }

        List<RespCustomer> respCustomerList = customerList.stream().map(mapper::toResponse).toList();
        response.setT(respCustomerList);
        response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }

    @Override
    public Response<RespCustomer> getCustomerById(Long customerId) {
        Response<RespCustomer> response = new Response<>();
        if (customerId == null) {
            throw new BankException(ExceptionConstant.INVALID_REQUEST_DATA, INVALID_REQUEST_DATA_MESSAGE);
        }

        Customer customer = customerRepository.findCustomerByIdAndActive(customerId, EnumAvailableStatus.ACTIVE.value);
        if (customer == null) {
            throw new BankException(ExceptionConstant.CUSTOMER_NOT_FOUND, CUSTOMER_NOT_FOUND_MESSAGE);
        }

        RespCustomer respCustomer = mapper.toResponse(customer);
        response.setT(respCustomer);
        response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }

    @Override
    public Response saveCustomer(ReqCustomer reqCustomer) {
        Response response = new Response();
        String name = reqCustomer.getName();
        String surname = reqCustomer.getSurname();
        if (name == null || surname == null) {
            throw new BankException(ExceptionConstant.INVALID_REQUEST_DATA, INVALID_REQUEST_DATA_MESSAGE);
        }

        Customer customer = mapper.toEntity(reqCustomer);

        customerRepository.save(customer);
        response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }

    @Override
    public Response updateCustomer(ReqCustomer reqCustomer) {
        Response response = new Response();
        Long id = reqCustomer.getId();
        String name = reqCustomer.getName();
        String surname = reqCustomer.getSurname();
        if (id == null || name == null || surname == null) {
            throw new BankException(ExceptionConstant.INVALID_REQUEST_DATA, INVALID_REQUEST_DATA_MESSAGE);
        }

        Customer customer = customerRepository.findCustomerByIdAndActive(id, EnumAvailableStatus.ACTIVE.value);
        if (customer == null) {
            throw new BankException(ExceptionConstant.CUSTOMER_NOT_FOUND, CUSTOMER_NOT_FOUND_MESSAGE);
        }

        customer.setName(reqCustomer.getName());
        customer.setSurname(reqCustomer.getSurname());
        customer.setDob(reqCustomer.getDob());
        customer.setAddress(customer.getAddress());
        customer.setPhone(reqCustomer.getPhone());
        customer.setCif(reqCustomer.getCif());
        customer.setPin(reqCustomer.getPin());
        customer.setSeria(reqCustomer.getSeria());
        customerRepository.save(customer);

        response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }

    @Override
    public Response deleteCustomer(Long customerId) {
        Response response = new Response();
        if (customerId == null) {
            throw new BankException(ExceptionConstant.INVALID_REQUEST_DATA, INVALID_REQUEST_DATA_MESSAGE);
        }

        Customer customer = customerRepository.findCustomerByIdAndActive(customerId, EnumAvailableStatus.ACTIVE.value);
        if (customer == null) {
            throw new BankException(ExceptionConstant.CUSTOMER_NOT_FOUND, CUSTOMER_NOT_FOUND_MESSAGE);
        }

        customer.setActive(EnumAvailableStatus.DEACTIVE.value);
        customerRepository.save(customer);
        response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }
}
