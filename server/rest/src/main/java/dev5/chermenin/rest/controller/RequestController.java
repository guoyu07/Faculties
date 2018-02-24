package dev5.chermenin.rest.controller;

import dev5.chermenin.service.api.UserService;
import dev5.chermenin.service.dto.impl.user.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Ancarian on 06.12.2017.
 */

@RestController

@Api(value = "users", description = "Admin request controller")
public class RequestController {

    private UserService userService;

    @Autowired
    public RequestController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "get requests")
    @RequestMapping(value = {"/requests"}, method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> requests() {
        return new ResponseEntity<>(userService.getRequests(), HttpStatus.OK);
    }

    @ApiOperation(value = "confirm request")
    @RequestMapping(value = "/requests/{userId}/confirm", method = RequestMethod.PUT)
    public ResponseEntity confirmRequest(@PathVariable(value = "userId") long userId) {
        userService.changeStateOfRequest(userId, true);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "cancel request")
    @RequestMapping(value = "/requests/{userId}/cancel", method = RequestMethod.PUT)
    public ResponseEntity cancelRequest(@PathVariable(value = "userId") long userId) {

        //UserDto userDto = userService.findById(userId);
        userService.changeStateOfRequest(userId, false);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
