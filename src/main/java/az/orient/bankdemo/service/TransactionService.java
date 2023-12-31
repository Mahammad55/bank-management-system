package az.orient.bankdemo.service;

import az.orient.bankdemo.dto.request.ReqTransaction;
import az.orient.bankdemo.dto.response.RespStatus;
import az.orient.bankdemo.dto.response.RespTransaction;
import az.orient.bankdemo.dto.response.Response;

import java.util.List;

public interface TransactionService {
    Response<List<RespTransaction>> getTransactionList(Long accountId);

    RespStatus saveTransaction(ReqTransaction reqTransaction);
}
