package az.orient.bankdemo.service;

import az.orient.bankdemo.dto.request.ReqCustomer;
import az.orient.bankdemo.dto.request.ReqToken;
import az.orient.bankdemo.dto.response.RespCustomer;
import az.orient.bankdemo.dto.response.RespStatus;
import az.orient.bankdemo.dto.response.Response;

import java.util.List;

public interface CustomerService {

    Response<List<RespCustomer>> getCustomerList(ReqToken reqToken);

    Response<RespCustomer> getCustomerById(ReqCustomer reqCustomer);

    RespStatus saveCustomer(ReqCustomer reqCustomer);

    RespStatus updateCustomer(ReqCustomer reqCustomer);

    RespStatus deleteCustomer(Long customerId);

}
