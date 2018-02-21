package dev5.chermenin.rest.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import dev5.chermenin.service.api.GroupService;
import dev5.chermenin.service.dto.View;
import dev5.chermenin.service.dto.impl.GroupDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Ancarian on 06.12.2017.
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Api(description = "Group controller")
public class GroupController {
    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @JsonView(View.Enable.class)
    @ApiOperation(value = "get all groups")
    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public ResponseEntity<List<GroupDto>> allGroups(Pageable pageable) throws JsonProcessingException {
        List<GroupDto> groupDtos = groupService.findAll(pageable);
        return new ResponseEntity<>(groupDtos, HttpStatus.OK);
    }

    @ApiOperation(value = "get group by id")
    @RequestMapping(value = "/groups/{groupId}", method = RequestMethod.GET)
    public ResponseEntity<GroupDto> getGroup(@PathVariable(value = "groupId") long id) {
        GroupDto groupDto = groupService.findById(id);
        return new ResponseEntity<>(groupDto, HttpStatus.OK);
    }

    @ApiOperation(value = "save new group")
    @RequestMapping(value = "/groups", method = RequestMethod.POST)
    public ResponseEntity<GroupDto> addGroup(@Valid @RequestBody GroupDto groupDto) {
        return new ResponseEntity<>(groupService.save(groupDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "remove group")
    @RequestMapping(value = "/groups/{groupId}", method = RequestMethod.DELETE)
    public ResponseEntity removeGroup(@PathVariable(value = "groupId") long id) {
        groupService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @JsonView(View.Enable.class)
    @ApiOperation(value = "update group")
    @RequestMapping(value = "/groups/", method = RequestMethod.PUT)
    public ResponseEntity updateGroup(@Valid @RequestBody GroupDto groupDto) {
        groupService.update(groupDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
