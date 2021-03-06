package dev5.chermenin.rest.controller;

import dev5.chermenin.rest.security.service.AuthenticationServiceImpl;
import dev5.chermenin.service.api.UserService;
import dev5.chermenin.service.dto.impl.login.LoginRequestDto;
import dev5.chermenin.service.dto.impl.login.LoginResponseDto;
import dev5.chermenin.service.dto.impl.user.ProfileUserDto;
import dev5.chermenin.service.dto.impl.user.RegisterDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by Ancarian on 18.02.2018.
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "auth", description = "Auth controller")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationServiceImpl authenticationService;
    private final UserService userService;

    @ApiOperation(value = "login")
    @PostMapping(value = "/login")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<LoginResponseDto> login(@RequestBody @NotNull final LoginRequestDto loginRequestDto) {
        return new ResponseEntity<>(authenticationService.login(loginRequestDto), HttpStatus.OK);
    }

    @ApiOperation(value = "sign up")
    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
    public ResponseEntity<RegisterDto> register(@Valid @NotNull @RequestBody RegisterDto userDto) {

        return new ResponseEntity<>(userService.save(userDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "profile")
    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/me")
    public ResponseEntity<ProfileUserDto> me() {
        return new ResponseEntity<>(authenticationService.getMe(), HttpStatus.OK);
    }

}