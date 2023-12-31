package az.orient.bankdemo.controller;

import az.orient.bankdemo.dto.request.ReqToken;
import az.orient.bankdemo.dto.request.ReqUser;
import az.orient.bankdemo.dto.response.RespStatus;
import az.orient.bankdemo.dto.response.RespUser;
import az.orient.bankdemo.dto.response.Response;
import az.orient.bankdemo.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {
    UserService userService;

    @PostMapping("/auth")
    public Response<RespUser> auth(@RequestBody ReqUser reqUser) {
        return userService.auth(reqUser);
    }

    @PostMapping("/logout")
    public RespStatus logout(@RequestBody ReqToken reqToken){
        return userService.logout(reqToken);
    }
}
