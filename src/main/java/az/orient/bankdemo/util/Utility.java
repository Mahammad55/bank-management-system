package az.orient.bankdemo.util;

import az.orient.bankdemo.dto.request.ReqToken;
import az.orient.bankdemo.entity.User;
import az.orient.bankdemo.entity.UserToken;
import az.orient.bankdemo.enums.EnumAvailableStatus;
import az.orient.bankdemo.exception.BankException;
import az.orient.bankdemo.exception.ExceptionConstant;
import az.orient.bankdemo.exception.ExceptionMessage;
import az.orient.bankdemo.repository.UserRepository;
import az.orient.bankdemo.repository.UserTokenRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Utility {
    UserRepository userRepository;

    UserTokenRepository userTokenRepository;

    public UserToken checkToken(ReqToken reqToken) {
        Long userId = reqToken.getUserId();
        String token = reqToken.getToken();
        if (userId == null || token == null) {
            throw new BankException(ExceptionConstant.INVALID_REQUEST_DATA, ExceptionMessage.INVALID_REQUEST_DATA_MESSAGE);
        }

        User user = userRepository.findUserByIdAndActive(userId, EnumAvailableStatus.ACTIVE.value);
        if (user == null) {
            throw new BankException(ExceptionConstant.USER_NOT_FOUND, ExceptionMessage.USER_NOT_FOUND_MESSAGE);
        }

        UserToken userToken = userTokenRepository.findUserTokenByUserAndTokenAndActive(user, token, EnumAvailableStatus.ACTIVE.value);
        if (userToken == null) {
            throw new BankException(ExceptionConstant.INVALID_TOKEN, ExceptionMessage.INVALID_TOKEN_MESSAGE);
        }

        return userToken;
    }
}
