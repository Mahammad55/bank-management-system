package az.orient.bankdemo.service.impl;

import az.orient.bankdemo.dto.request.ReqTransaction;
import az.orient.bankdemo.dto.response.RespStatus;
import az.orient.bankdemo.dto.response.RespTransaction;
import az.orient.bankdemo.dto.response.Response;
import az.orient.bankdemo.entity.Account;
import az.orient.bankdemo.entity.Transaction;
import az.orient.bankdemo.enums.EnumAvailableStatus;
import az.orient.bankdemo.exception.BankException;
import az.orient.bankdemo.exception.ExceptionConstant;
import az.orient.bankdemo.exception.ExceptionMessage;
import az.orient.bankdemo.mapper.TransactionMapper;
import az.orient.bankdemo.repository.AccountRepository;
import az.orient.bankdemo.repository.TransactionRepository;
import az.orient.bankdemo.service.TransactionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class TransactionServiceImpl implements TransactionService {
    TransactionRepository transactionRepository;

    AccountRepository accountRepository;

    TransactionMapper mapper;

    @Override
    public Response<List<RespTransaction>> getTransactionList(Long accountId) {
        Response<List<RespTransaction>> response = new Response<>();
        if (accountId == null) {
            throw new BankException(ExceptionConstant.INVALID_REQUEST_DATA,  ExceptionMessage.INVALID_REQUEST_DATA_MESSAGE);
        }

        Account account = accountRepository.findAccountByIdAndActive(accountId, EnumAvailableStatus.ACTIVE.value);
        if (account == null) {
            throw new BankException(ExceptionConstant.ACCOUNT_NOT_FOUND,  ExceptionMessage.ACCOUNT_NOT_FOUND_MESSAGE);
        }

        List<Transaction> transactionList = transactionRepository.findAllByFromAccountAndActive(account, EnumAvailableStatus.ACTIVE.value);
        if (transactionList.isEmpty()) {
            throw new BankException(ExceptionConstant.TRANSACTION_NOT_FOUND,  ExceptionMessage.TRANSACTION_NOT_FOUND_MESSAGE);
        }

        List<RespTransaction> respTransactionList = transactionList.stream().map(mapper::toResponse).toList();
        response.setT(respTransactionList);
        response.setStatus(RespStatus.getSuccessMessage());

        return response;
    }

    @Override
    public RespStatus saveTransaction(ReqTransaction reqTransaction) {
        Long fromAccountId= reqTransaction.getFromAccountId();
        String toAccount= reqTransaction.getToAccount();
        BigDecimal amount= reqTransaction.getAmount();
        String iban= reqTransaction.getIban();
        if(fromAccountId==null ||toAccount==null|| amount==null || iban==null){
            throw new BankException(ExceptionConstant.INVALID_REQUEST_DATA,  ExceptionMessage.INVALID_REQUEST_DATA_MESSAGE);
        }

        Account account = accountRepository.findAccountByIdAndActive(fromAccountId, EnumAvailableStatus.ACTIVE.value);
        if(account==null){
            throw new BankException(ExceptionConstant.ACCOUNT_NOT_FOUND,  ExceptionMessage.ACCOUNT_NOT_FOUND_MESSAGE);
        }

        Transaction transaction=mapper.toEntity(reqTransaction);
        transactionRepository.save(transaction);

        return RespStatus.getSuccessMessage();
    }
}

