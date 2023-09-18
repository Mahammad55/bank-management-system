package az.orient.bankdemo.service.impl;

import az.orient.bankdemo.dto.request.ReqAccount;
import az.orient.bankdemo.dto.response.RespAccount;
import az.orient.bankdemo.dto.response.RespStatus;
import az.orient.bankdemo.dto.response.Response;
import az.orient.bankdemo.entity.Account;
import az.orient.bankdemo.entity.Customer;
import az.orient.bankdemo.enums.EnumAvailableStatus;
import az.orient.bankdemo.exception.BankException;
import az.orient.bankdemo.exception.ExceptionConstant;
import az.orient.bankdemo.mapper.AccountMapper;
import az.orient.bankdemo.repository.AccountRepository;
import az.orient.bankdemo.repository.CustomerRepository;
import az.orient.bankdemo.service.AccountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AccountServiceImpl implements AccountService {
    AccountRepository accountRepository;

    CustomerRepository customerRepository;

    AccountMapper mapper;
    private static final String CUSTOMER_NOT_FOUND_MESSAGE = "Customer not found!";
    private static final String ACCOUNT_NOT_FOUND_MESSAGE = "Account not found!";
    private static final String INVALID_REQUEST_DATA_MESSAGE = "Invalid request data";

    @Override
    public Response<List<RespAccount>> getAccountsListByCustomerId(Long customerId) {
        Response<List<RespAccount>> response = new Response<>();
        if (customerId == null) {
            throw new BankException(ExceptionConstant.INVALID_REQUEST_DATA, INVALID_REQUEST_DATA_MESSAGE);
        }

        Customer customer = customerRepository.findCustomerByIdAndActive(customerId, EnumAvailableStatus.ACTIVE.value);
        if (customer == null) {
            throw new BankException(ExceptionConstant.CUSTOMER_NOT_FOUND, CUSTOMER_NOT_FOUND_MESSAGE);
        }

        List<Account> accountList = accountRepository.findAllByCustomerAndActive(customer, EnumAvailableStatus.ACTIVE.value);
        if (accountList.isEmpty()) {
            throw new BankException(ExceptionConstant.ACCOUNT_NOT_FOUND, ACCOUNT_NOT_FOUND_MESSAGE);
        }

        List<RespAccount> respAccountList = accountList.stream().map(mapper::toResponse).toList();
        response.setT(respAccountList);
        response.setStatus(RespStatus.getSuccessMessage());

        return response;
    }

    @Override
    public Response saveAccount(ReqAccount reqAccount) {
        Response response = new Response();

        Long customerId = reqAccount.getCustomerId();
        String name = reqAccount.getName();
        String accountNo = reqAccount.getAccountNo();
        if (customerId == null || name == null || accountNo == null) {
            throw new BankException(ExceptionConstant.INVALID_REQUEST_DATA, INVALID_REQUEST_DATA_MESSAGE);
        }

        Customer customer = customerRepository.findCustomerByIdAndActive(customerId, EnumAvailableStatus.ACTIVE.value);
        if (customer == null) {
            throw new BankException(ExceptionConstant.CUSTOMER_NOT_FOUND, CUSTOMER_NOT_FOUND_MESSAGE);
        }

        Account account = mapper.toEntity(reqAccount);
        account.setCustomer(customer);
        accountRepository.save(account);

        response.setStatus(RespStatus.getSuccessMessage());
        return response;
    }
}
