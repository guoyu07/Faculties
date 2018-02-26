package dev5.chermenin.rest.controller;

import dev5.chermenin.service.api.FacultyService;
import dev5.chermenin.service.dto.impl.FacultyDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Api(description = "Faculty controller")
@RequestMapping("/faculties")
@RequiredArgsConstructor
public class FacultyController {
    private final FacultyService facultyService;

    @ApiOperation(value = "get all faculties")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<FacultyDto>> getAllFaculties(Pageable pageable) {
        List<FacultyDto> facultyDtos = facultyService.findAll(pageable);
        return new ResponseEntity<>(facultyDtos, HttpStatus.OK);
    }

    @ApiOperation(value = "get faculty by id")
    @RequestMapping(value = "/{facultyId}", method = RequestMethod.GET)
    public ResponseEntity<FacultyDto> getFaculty(@PathVariable(value = "facultyId") long id) {
        FacultyDto facultyDto = facultyService.findById(id);
        return new ResponseEntity<>(facultyDto, HttpStatus.OK);
    }
}
