package az.orient.bankdemo.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReqTransaction {
    Long fromAccountId;

    String toAccount;

    BigDecimal amount;

    String iban;
}
