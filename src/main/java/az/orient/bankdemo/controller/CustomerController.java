package az.orient.bankdemo.controller;

import az.orient.bankdemo.dto.request.ReqCustomer;
import az.orient.bankdemo.dto.response.RespCustomer;
import az.orient.bankdemo.dto.response.Response;
import az.orient.bankdemo.service.CustomerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/customers")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CustomerController {
    CustomerService customerService;

    @GetMapping
    public Response<List<RespCustomer>> getCustomerList() {
        return customerService.getCustomerList();
    }

    @GetMapping("/{id}")
    public Response<RespCustomer> getCustomerById(@PathVariable(value = "id",required = false   ) Long customerId){
        return customerService.getCustomerById(customerId);
    }

    @PostMapping
    public Response saveCustomer(@RequestBody ReqCustomer reqCustomer){
        return customerService.saveCustomer(reqCustomer);
    }

    @PutMapping
    public Response updateCustomer(@RequestBody ReqCustomer reqCustomer){
        return customerService.updateCustomer(reqCustomer);
    }

    @DeleteMapping("/{id}")
    public Response deleteCustomer(@PathVariable(value = "id",required = false) Long customerId){
        return customerService.deleteCustomer(customerId);
    }
}
