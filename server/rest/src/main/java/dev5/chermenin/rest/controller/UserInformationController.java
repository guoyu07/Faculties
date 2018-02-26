package dev5.chermenin.rest.controller;

import dev5.chermenin.rest.security.service.AuthenticationServiceImpl;
import dev5.chermenin.service.api.UserInformationService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Ancarian on 26.02.2018.
 */

@RestController
@RequestMapping(value = "/settings")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UserInformationController {

    private final UserInformationService userInformationService;
    private final AuthenticationServiceImpl authenticationService;

    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "change password")
    @RequestMapping(value = "/change_password/{password}/{oldPassword}", method = RequestMethod.PUT)
    public ResponseEntity changePassword(@PathVariable(value = "password") String password,
                                         @PathVariable(value = "oldPassword") String oldPassword) {
        userInformationService.changePassword(authenticationService.getMyId(), password, oldPassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "change email")
    @RequestMapping(value = "/change_email/{newEmail}/{oldEmail}", method = RequestMethod.PUT)
    public ResponseEntity changeEmail(@PathVariable(value = "newEmail") String newEmail,
                                      @PathVariable(value = "oldEmail") String oldEmail) {
        userInformationService.changeEmail(authenticationService.getMyId(), newEmail, oldEmail);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
