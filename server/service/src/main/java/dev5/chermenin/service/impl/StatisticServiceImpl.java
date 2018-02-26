package dev5.chermenin.service.impl;

import dev5.chermenin.dao.repository.GroupRepository;
import dev5.chermenin.dao.repository.UserRepository;
import dev5.chermenin.model.entity.impl.Group;
import dev5.chermenin.model.entity.impl.User;
import dev5.chermenin.service.api.GroupService;
import dev5.chermenin.service.api.StatisticService;
import dev5.chermenin.service.dto.impl.GroupDto;
import dev5.chermenin.service.dto.impl.user.UserDto;
import dev5.chermenin.service.exceptions.NotFoundException;
import dev5.chermenin.service.util.converters.Converter;
import dev5.chermenin.service.util.converters.impl.GroupConverter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Ancarian on 13.10.2017.
 */

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {
    private final GroupService groupService;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final Converter<GroupDto, Group> groupConverter;
    private final Converter<UserDto, User> userConverter;
    private final Logger logger = LoggerFactory.getLogger(StatisticServiceImpl.class);

    @Override
    public List<GroupDto> getTopGroupsByMark(Pageable pageable) {
        List<GroupDto> groups = groupService.findAll(pageable);
        groups.sort(Comparator.comparing(GroupDto::getEnrollMark));
        return groups;
    }

    @Override
    public List<GroupDto> getTopGroupsByAvgMark() {
        return groupConverter.convertToDto(groupRepository.getTopGroupsByAvgMark());
    }

    @Transactional
    @Override
    public List<UserDto> getEnrollUsers(long id) {
        if (!groupRepository.exists(id)) {
            logger.error("group with id: {} not found", id);
            throw new NotFoundException(String.format("group with id: %d not found", id));
        }

        return userConverter.convertToDto(userRepository.getEnrollUsers(id));
    }

}
