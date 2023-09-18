package az.orient.bankdemo.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BankException extends RuntimeException{
    Integer code;
    public BankException(String message) {
        super(message);
    }

    public BankException(Integer code,String message) {
        super(message);
        this.code=code;
    }

    public Integer getCode() {
        return code;
    }
}
