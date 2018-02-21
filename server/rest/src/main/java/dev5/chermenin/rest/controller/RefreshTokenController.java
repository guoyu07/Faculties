package dev5.chermenin.rest.controller;

import dev5.chermenin.rest.security.service.AuthenticationServiceImpl;
import dev5.chermenin.service.dto.impl.login.LoginRequestDto;
import dev5.chermenin.service.dto.impl.login.LoginResponseDto;
import dev5.chermenin.service.dto.impl.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Ancarian on 18.02.2018.
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RefreshTokenController {

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public LoginResponseDto refreshAuthenticationToken(HttpServletRequest request, HttpServletResponse response) {

        UserDto userDto = authenticationService.getMe();
        LoginRequestDto loginRequestDto = new LoginRequestDto(userDto.getInfo().getNickname(), userDto.getInfo().getPassword());
        return authenticationService.login(loginRequestDto);
    }
}
