package az.orient.bankdemo.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RespCustomer {
    Long customerId;

    String name;

    String surname;

    String address;

    Date dob;

    String phone;

    String pin;

    String seria;

    String cif;
}
