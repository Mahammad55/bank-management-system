package az.orient.bankdemo.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RespAccount {
    Long id;

    String name;

    String accountNo;

    String iban;

    String currency;

    RespCustomer respCustomer;
}
