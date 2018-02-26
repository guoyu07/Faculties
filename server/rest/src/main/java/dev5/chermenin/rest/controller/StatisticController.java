package dev5.chermenin.rest.controller;

import dev5.chermenin.service.api.StatisticService;
import dev5.chermenin.service.dto.impl.GroupDto;
import dev5.chermenin.service.dto.impl.user.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Ancarian on 29.11.2017.
 */

@RestController
@RequestMapping("statistic")
@Api(description = "Statistic controller")
public class StatisticController {

    private StatisticService statisticService;

    @Autowired
    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

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
