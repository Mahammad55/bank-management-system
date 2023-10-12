package az.orient.bankdemo.service;

import az.orient.bankdemo.dto.request.ReqAccount;
import az.orient.bankdemo.dto.response.RespAccount;
import az.orient.bankdemo.dto.response.RespStatus;
import az.orient.bankdemo.dto.response.Response;

import java.util.List;

public interface AccountService {
    Response<List<RespAccount>> getAccountsListByCustomerId(Long customerId);

    RespStatus saveAccount(ReqAccount reqAccount);
}
