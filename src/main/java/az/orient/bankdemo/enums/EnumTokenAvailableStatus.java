package az.orient.bankdemo.enums;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PUBLIC)
public enum EnumTokenAvailableStatus {
    ACTIVE(1),
    DEACTIVE(0),
    EXPIRED(2);

    int value;
}
