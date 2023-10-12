package az.orient.bankdemo.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReqCustomer {
    Long id;

    String name;

    String surname;

    String address;

    Date dob;

    String phone;

    String pin;

    String seria;

    String cif;

    ReqToken reqToken;
}
