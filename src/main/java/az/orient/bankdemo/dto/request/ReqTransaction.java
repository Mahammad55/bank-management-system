package az.orient.bankdemo.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReqTransaction {
    Long fromAccountId;

    String toAccount;

    Double amount;

    String iban;
}
