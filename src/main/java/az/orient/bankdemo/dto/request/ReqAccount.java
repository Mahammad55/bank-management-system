package az.orient.bankdemo.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReqAccount {
    Long id;

    String name;

    String accountNo;

    String iban;

    String currency;

    Long customerId;
}
