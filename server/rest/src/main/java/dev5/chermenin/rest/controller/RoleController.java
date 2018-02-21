//package dev5.chermenin.rest.controller;
//
//import dev5.chermenin.service.api.RoleService;
//import dev5.chermenin.service.dto.impl.RoleDto;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.Authorization;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
///**
// * Created by Ancarian on 24.12.2017.
// */
//
//@RestController
//@RequestMapping("roles")
//@Api(description = "Role controller", authorizations = {@Authorization(value = "basicAuth")})
//public class RoleController {
//
//    private RoleService roleService;
//
//    @Autowired
//    public RoleController(RoleService roleService) {
//        this.roleService = roleService;
//    }
//
//    @ApiOperation(value = "get all roles")
//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<List<RoleDto>> getAll() {
//        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
//    }
//
//    @ApiOperation(value = "find role by id")
//    @RequestMapping(value = "/{roleId}", method = RequestMethod.GET)
//    public ResponseEntity<RoleDto> findByRoleId(@PathVariable(value = "roleId") long id) {
//        return new ResponseEntity<>(roleService.findById(id), HttpStatus.OK);
//    }
//
//    @ApiOperation(value = "find role by name")
//    @RequestMapping(value = "/{roleName}", method = RequestMethod.GET)
//    public ResponseEntity<RoleDto> findByRoleName(@PathVariable(value = "roleName") String roleName) {
//        return new ResponseEntity<>(roleService.findByName(roleName), HttpStatus.OK);
//    }
//
//    @ApiOperation(value = "add role to user")
//    @RequestMapping(value = "/{user_id}/{user_role}", method = RequestMethod.PUT)
//    public ResponseEntity addRoleToUser(@PathVariable(value = "user_id") long user_id, @PathVariable(value = "user_role") long user_role) {
//        roleService.addRoleToUser(user_id, user_role);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @ApiOperation(value = "remove user role")
//    @RequestMapping(value = "/{user_id}/{user_role}", method = RequestMethod.DELETE)
//    public ResponseEntity removeUserRole(@PathVariable(value = "user_id") long user_id, @PathVariable(value = "user_role") long user_role) {
//        roleService.removeUserRole(user_id, user_role);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//}
