package az.orient.bankdemo.exception;

import az.orient.bankdemo.dto.response.RespStatus;
import az.orient.bankdemo.dto.response.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionHandlerClass {

    @ExceptionHandler(BankException.class)
    public RespStatus bankExceptionHandler(BankException ex) {
        return new RespStatus(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public RespStatus exceptionHandler() {
        return new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, ExceptionMessage.INTERNAL_EXCEPTION_MESSAGE);
    }
}
