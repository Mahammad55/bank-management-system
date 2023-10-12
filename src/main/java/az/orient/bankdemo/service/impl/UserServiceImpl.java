package az.orient.bankdemo.service.impl;

import az.orient.bankdemo.dto.request.ReqToken;
import az.orient.bankdemo.dto.request.ReqUser;
import az.orient.bankdemo.dto.response.RespStatus;
import az.orient.bankdemo.dto.response.RespToken;
import az.orient.bankdemo.dto.response.RespUser;
import az.orient.bankdemo.dto.response.Response;
import az.orient.bankdemo.entity.User;
import az.orient.bankdemo.entity.UserToken;
import az.orient.bankdemo.enums.EnumAvailableStatus;
import az.orient.bankdemo.enums.EnumTokenAvailableStatus;
import az.orient.bankdemo.exception.BankException;
import az.orient.bankdemo.exception.ExceptionConstant;
import az.orient.bankdemo.exception.ExceptionMessage;
import az.orient.bankdemo.repository.UserRepository;
import az.orient.bankdemo.repository.UserTokenRepository;
import az.orient.bankdemo.service.UserService;
import az.orient.bankdemo.util.Utility;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    UserTokenRepository userTokenRepository;

    Utility utility;

    @Override
    public Response<RespUser> auth(ReqUser reqUser) {
        Response<RespUser> response = new Response<>();
        String username = reqUser.getUsername();
        String password = reqUser.getPassword();
        if (username == null || password == null) {
            throw new BankException(ExceptionConstant.INVALID_REQUEST_DATA, ExceptionMessage.INVALID_REQUEST_DATA_MESSAGE);
        }

        User user = userRepository.findUserByUsernameAndPasswordAndActive(username, password, EnumAvailableStatus.ACTIVE.value);
        if (user == null) {
            throw new BankException(ExceptionConstant.USER_NOT_FOUND, ExceptionMessage.USER_NOT_FOUND_MESSAGE);
        }

        String token = UUID.randomUUID().toString();
        UserToken userToken = UserToken.builder()
                .user(user)
                .token(token)
                .build();
        userTokenRepository.save(userToken);

        RespUser respUser = RespUser.builder()
                .username(username)
                .fullName(user.getFullName())
                .respToken(new RespToken(user.getId(), token))
                .build();

        response.setT(respUser);
        response.setStatus(RespStatus.getSuccessMessage());

        return response;
    }

    @Override
    public RespStatus logout(ReqToken reqToken) {
        UserToken userToken = utility.checkToken(reqToken);
        userToken.setActive(EnumTokenAvailableStatus.DEACTIVE.value);
        userTokenRepository.save(userToken);

        return RespStatus.getSuccessMessage();
    }
}
