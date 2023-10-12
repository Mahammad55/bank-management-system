package az.orient.bankdemo.controller;

import az.orient.bankdemo.dto.request.ReqAccount;
import az.orient.bankdemo.dto.response.RespAccount;
import az.orient.bankdemo.dto.response.RespStatus;
import az.orient.bankdemo.dto.response.Response;
import az.orient.bankdemo.service.AccountService;
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
@RequestMapping("v1/accounts")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class AccountController {
    AccountService accountService;

    @GetMapping("/{id}")
    public Response<List<RespAccount>> getAccountsListByCustomerId(@PathVariable(value = "id",required = false) Long customerId){
        return accountService.getAccountsListByCustomerId(customerId);
    }

    @PostMapping
    public RespStatus saveAccount(@RequestBody ReqAccount reqAccount){
        return accountService.saveAccount(reqAccount);
    }
}
