package az.orient.bankdemo.exception;

import az.orient.bankdemo.dto.response.RespStatus;
import az.orient.bankdemo.dto.response.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionHandlerClass {

    @ExceptionHandler(BankException.class)
    public Response bankExceptionHandler(BankException ex) {
        Response response = new Response<>();
        response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        return response;
    }

    @ExceptionHandler(Exception.class)
    public Response exceptionHandler() {
        Response response = new Response<>();
        response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal server exception"));
        return response;
    }
}
