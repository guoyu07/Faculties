package dev5.chermenin.rest.controller;

import dev5.chermenin.service.api.UserInformationService;
import dev5.chermenin.service.api.UserService;
import dev5.chermenin.service.dto.impl.user.ProfileUserDto;
import dev5.chermenin.service.dto.impl.user.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Ancarian on 22.10.2017.
 */

@RestController
@RequestMapping("/users")
@Api(value = "/users", description = "User controller")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private UserInformationService userInformationService;
    private UserService userService;

    @Autowired
    public UserController(UserInformationService userInformationService, UserService userService) {
        this.userInformationService = userInformationService;
        this.userService = userService;
    }

    @ApiOperation(value = "add request", authorizations = {@Authorization(value = "basicAuth")})
    @RequestMapping(value = "/{userId}/{groupId}", method = RequestMethod.PUT, name = "")
    public ResponseEntity<UserDto> addRequest(@PathVariable(value = "userId") long userId, @PathVariable(value = "groupId") long groupId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (userInformationService.findByNickname(authentication.getName()).getId() != userId) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        userService.selectGroup(userId, groupId);
        return new ResponseEntity<>(userService.findById(userId), HttpStatus.OK);
    }

    @ApiOperation(value = "find user by Id", authorizations = {@Authorization(value = "basicAuth")})
    @RequestMapping(value = "/id/{userId}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> findById(@PathVariable(value = "userId") long userId) {
        UserDto userDto = userService.findById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @ApiOperation(value = "find user by name", authorizations = {@Authorization(value = "basicAuth")})
    @RequestMapping(value = "/name/{userName}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> findByName(@PathVariable(value = "userName") String userName) {
        return new ResponseEntity<>(userService.findById(userInformationService.findByNickname(userName).getId()), HttpStatus.OK);
    }

    @ApiOperation(value = "get all users")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> allUsers(Pageable pageable) {
        return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "remove user", authorizations = {@Authorization(value = "basicAuth")})
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity removeUser(@PathVariable(value = "userId") long id) {
        userService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "update user", authorizations = {@Authorization(value = "basicAuth")})
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity updateUser(@Valid @RequestBody ProfileUserDto userDto) {
        userService.update(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "get profile user", authorizations = {@Authorization(value = "basicAuth")})
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ResponseEntity<ProfileUserDto> getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        return new ResponseEntity<>(userService.findProfileById(userInformationService.findByNickname(currentUserName).getId()), HttpStatus.OK);
    }
}
