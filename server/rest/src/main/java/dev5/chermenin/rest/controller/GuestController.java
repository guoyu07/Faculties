package dev5.chermenin.rest.controller;

import dev5.chermenin.service.api.UserService;
import dev5.chermenin.service.dto.impl.user.ProfileUserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by Ancarian on 30.11.2017.
 */
@RestController
@RequestMapping("/guest")
@Api(value = "guests", description = "Guest controller")
public class GuestController {

    private UserService userService;

    public GuestController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "save new user")
    @RequestMapping(value = "/guest/register", method = RequestMethod.POST)
    public ResponseEntity<ProfileUserDto> addUser(@Valid @RequestBody ProfileUserDto userDto, @RequestParam String password, @RequestParam String email) {

        userDto.getInfo().setEmail(email);
        userDto.getInfo().setPassword(password);
        return new ResponseEntity<>(userService.save(userDto), HttpStatus.CREATED);
    }

    @RequestMapping("/login")
    public Principal user(Principal principal) {
        return principal;
    }
}
