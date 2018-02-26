package dev5.chermenin.rest.controller;

import dev5.chermenin.service.api.SubjectService;
import dev5.chermenin.service.dto.subject.SubjectDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Ancarian on 24.12.2017.
 */

@RestController
@RequestMapping("/subjects")
@Api(description = "Subject controller")
public class SubjectController {
    private SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @ApiOperation(value = "save new subject")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<SubjectDto> saveSubject(@Valid @RequestBody SubjectDto subjectDto) {
        return new ResponseEntity<>(subjectService.save(subjectDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "get all subjects")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<SubjectDto>> getAll(Pageable pageable) {
        return new ResponseEntity<>(subjectService.findAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "remove subject")
    @RequestMapping(value = "/{subjectId}", method = RequestMethod.DELETE)
    public ResponseEntity removeSubject(@PathVariable(value = "subjectId") long id) {
        subjectService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "find subject by id")
    @RequestMapping(value = "/{subjectId}", method = RequestMethod.GET)
    public ResponseEntity<SubjectDto> findSubjectById(@PathVariable(value = "subjectId") long id) {
        return new ResponseEntity<>(subjectService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "find subject by name")
    @RequestMapping(value = "/{subjectName}", method = RequestMethod.GET)
    public ResponseEntity<SubjectDto> findSubjectByName(@PathVariable(value = "subjectName") String subjectName) {
        return new ResponseEntity<>(subjectService.findByName(subjectName), HttpStatus.OK);
    }

    @ApiOperation(value = "addSubjectToUser")
    @RequestMapping(value = "/change_state", method = RequestMethod.PUT)
    public ResponseEntity addSubjectToUser(@RequestParam(value = "userid", required = false) long userId,
                                           @RequestParam(value = "subjectId", required = false) long subjectId,
                                           @RequestParam(value = "mark", required = false) int mark) {

        subjectService.addSubjectToUser(userId, subjectId, mark);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "removeUserSubject")
    @RequestMapping(value = "/change_state", method = RequestMethod.DELETE)
    public ResponseEntity removeUserSubject(@RequestParam(value = "userid", required = false) long userId,
                                            @RequestParam(value = "subjectId", required = false) long subjectId) {

        subjectService.removeUserSubject(userId, subjectId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
