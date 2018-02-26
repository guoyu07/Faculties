package dev5.chermenin.rest.controller;

import dev5.chermenin.service.api.StatisticService;
import dev5.chermenin.service.dto.impl.GroupDto;
import dev5.chermenin.service.dto.impl.user.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Ancarian on 29.11.2017.
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/statistic")
@Api(description = "Statistic controller")
@RequiredArgsConstructor
public class StatisticController {
    private final StatisticService statisticService;

    @ApiOperation(value = "top groups by mark")
    @RequestMapping(value = "/top_groups_by_mark", method = RequestMethod.GET)
    public List<GroupDto> getTopGroupsByMark(Pageable pageable) {
        return statisticService.getTopGroupsByMark(pageable);
    }

    @ApiOperation(value = "enroll users")
    @RequestMapping(value = "/enroll_users/{groupId}", method = RequestMethod.GET)
    public List<UserDto> getEnrollUsers(@PathVariable(value = "groupId") long groupId) {
        return statisticService.getEnrollUsers(groupId);
    }

}
