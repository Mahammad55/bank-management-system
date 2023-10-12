package az.orient.bankdemo.controller;

import az.orient.bankdemo.dto.request.ReqTransaction;
import az.orient.bankdemo.dto.response.RespStatus;
import az.orient.bankdemo.dto.response.RespTransaction;
import az.orient.bankdemo.dto.response.Response;
import az.orient.bankdemo.service.TransactionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/transactions")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TransactionController {
    TransactionService transactionService;

    @GetMapping("/{id}")
    public Response<List<RespTransaction>> getTransactionList(@PathVariable(value = "id", required = false) Long accountId) {
        return transactionService.getTransactionList(accountId);
    }

    @PostMapping
    public RespStatus saveTransaction(@RequestBody ReqTransaction reqTransaction) {
        return transactionService.saveTransaction(reqTransaction);
    }
}
