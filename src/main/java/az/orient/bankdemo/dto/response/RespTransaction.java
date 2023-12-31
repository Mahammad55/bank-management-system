package az.orient.bankdemo.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RespTransaction {
    RespAccount respAccount;

    String toAccount;

    Double amount;

    String iban;

}
