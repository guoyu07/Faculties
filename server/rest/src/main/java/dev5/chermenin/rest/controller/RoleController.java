package dev5.chermenin.rest.controller;

import dev5.chermenin.model.entity.impl.enums.Roles;
import dev5.chermenin.service.api.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ancarian on 24.12.2017.
 */

@RestController
@RequestMapping("roles")
@Api(description = "Role controller", authorizations = {@Authorization(value = "basicAuth")})
public class RoleController {

    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @ApiOperation(value = "add role to user")
    @RequestMapping(value = "/{user_id}/{user_role}", method = RequestMethod.PUT)
    public ResponseEntity addRoleToUser(@PathVariable(value = "user_id") long user_id, @PathVariable(value = "user_role") String user_role) {
        roleService.addRoleToUser(user_id, Roles.valueOf(user_role));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "remove user role")
    @RequestMapping(value = "/{user_id}/{user_role}", method = RequestMethod.DELETE)
    public ResponseEntity removeUserRole(@PathVariable(value = "user_id") long user_id, @PathVariable(value = "user_role") String user_role) {
        roleService.removeUserRole(user_id, Roles.valueOf(user_role));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
