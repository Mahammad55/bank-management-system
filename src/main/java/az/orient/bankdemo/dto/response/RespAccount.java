package az.orient.bankdemo.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RespAccount {
    String name;

    String accountNo;

    String iban;

    String currency;

    RespCustomer respCustomer;
}
