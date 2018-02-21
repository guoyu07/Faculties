package dev5.chermenin.rest.controller;

import dev5.chermenin.service.api.GroupService;
import dev5.chermenin.service.api.StatisticService;
import dev5.chermenin.service.api.UserService;
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

    private UserService userService;
    private GroupService groupService;
    private StatisticService statisticService;

    @Autowired
    public StatisticController(GroupService groupService, StatisticService statisticService, UserService userService) {
        this.groupService = groupService;
        this.statisticService = statisticService;
        this.userService = userService;
    }

    @ApiOperation(value = "top groups by mark", authorizations = {@Authorization(value = "basicAuth")})
    @RequestMapping(value = "/top_groups_by_mark", method = RequestMethod.GET)
    public List<GroupDto> getTopGroupsByMark(Pageable pageable) {

        return statisticService.getTopGroupsByMark(pageable);
    }

//    @ApiOperation(value = "top groups by count of users", authorizations = {@Authorization(value = "basicAuth")})
//    @RequestMapping(value = "/top_groups_by_count_of_users", method = RequestMethod.GET)
//    public Map<GroupDto, Integer> getTopGroupsByCountOfUsers() {
//
//        return statisticService.getTopGroupsByCountOfUsers();
//    }

    @ApiOperation(value = "enroll users", authorizations = {@Authorization(value = "basicAuth")})
    @RequestMapping(value = "/enroll_users/{groupId}", method = RequestMethod.GET)
    public List<UserDto> getEnrollUsers(@PathVariable(value = "groupId") long groupId) {

        return statisticService.getEnrollUsers(groupId);
    }

//    @ApiOperation(value = "get competition")
//    @RequestMapping(value = "/competition/{groupId}", method = RequestMethod.GET)
//    public List<Integer> getCompetition(@PathVariable(value = "groupId") long groupId) {
//
//        return statisticService.getCompetition(groupId);
//    }
//
//    @ApiOperation(value = "get enroll mark for group")
//    @RequestMapping(value = "/enroll_mark/{groupId}", method = RequestMethod.GET)
//    public int getEnrollMark(@PathVariable(value = "groupId") long groupId) {
//
//        return statisticService.getEnrollMark(groupId);
//    }

}
