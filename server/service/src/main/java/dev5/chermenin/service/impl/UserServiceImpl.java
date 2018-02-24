package dev5.chermenin.service.impl;


import dev5.chermenin.dao.repository.GroupRepository;
import dev5.chermenin.dao.repository.UserInformationRepository;
import dev5.chermenin.dao.repository.UserRepository;
import dev5.chermenin.model.entity.impl.User;
import dev5.chermenin.model.entity.impl.UserInformation;
import dev5.chermenin.model.entity.impl.enums.Roles;
import dev5.chermenin.service.api.GroupService;
import dev5.chermenin.service.api.UserService;
import dev5.chermenin.service.dto.impl.user.ProfileUserDto;
import dev5.chermenin.service.dto.impl.user.UserDto;
import dev5.chermenin.service.exceptions.ExistsException;
import dev5.chermenin.service.exceptions.NotFoundException;
import dev5.chermenin.service.util.converters.Converter;
import dev5.chermenin.service.util.converters.impl.ProfileUserConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Ancarian on 10.10.2017.
 */

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private GroupRepository groupRepository;
    private GroupService groupService;
    private UserInformationRepository userInformationRepository;
    private Converter<UserDto, User> userConverter;
    private ProfileUserConverter profileUserConverter;
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(UserRepository userRepository, Converter<UserDto, User> userConverter, GroupRepository groupRepository, GroupService groupService, UserInformationRepository userInformationRepository, ProfileUserConverter profileUserConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.groupRepository = groupRepository;
        this.userInformationRepository = userInformationRepository;
        this.groupService = groupService;
        this.profileUserConverter = profileUserConverter;
    }

    @Transactional
    @Override
    public UserDto findById(long id) {
        if (!userRepository.exists(id)) {
            logger.error("user with id: {} not found", id);
            throw new NotFoundException(String.format("user with id: %d not found", id));
        }

        logger.info("user with id: {} found", id);
        User user = userRepository.findOne(id);
        return userConverter.convertToDto(user);
    }

    @Transactional
    @Override
    public ProfileUserDto findProfileById(long id) {
        if (!userRepository.exists(id)) {
            logger.error("user with id: {} not found", id);
            throw new NotFoundException(String.format("user with id: %d not found", id));
        }

        logger.info("user with id: {} found", id);
        User user = userRepository.findOne(id);
        return profileUserConverter.convertToDto(user);
    }

    @Override
    @Transactional
    public ProfileUserDto save(ProfileUserDto userDto) {
        if (userDto == null) {
            logger.error("user can't be null");
            throw new IllegalArgumentException("user can't be null");
        }

        if (userInformationRepository.findByEmail(userDto.getInfo().getEmail()) != null) {
            throw new ExistsException("user with this email exists");
        }
        if (userInformationRepository.findByNickname(userDto.getInfo().getNickname()) != null) {
            throw new ExistsException("user with this nickname exists");
        }

        User user = profileUserConverter.convertToEntity(userDto);

        Set<Roles> roles = new HashSet<>();
        roles.add(Roles.USER);
        user.getInfo().setRoles(roles);

        userRepository.save(user);
        userDto.setId(user.getId());
        logger.info("user with id: {} saved", userDto.getId());
        return userDto;
    }

    @Transactional
    @Override
    public List<UserDto> findAll(Pageable pageable) {
        List<User> users = userRepository.findAll(pageable).getContent();
        logger.info("all users found");
        return userConverter.convertToDto(users);
    }

    @Override
    @Transactional
    public void remove(long id) {
        if (userRepository.exists(id)) {
            userRepository.delete(id);
            logger.info("user with id: {} deleted", id);
        } else {
            logger.error("user with id: {} not found", id);
            throw new NotFoundException(String.format("user with id: %d not found", id));
        }
    }

    @Transactional
    public void changeStateOfRequest(long userId, boolean value) {
        User user = userRepository.findOne(userId);
        if (user.getGroup() == null) {
            throw new ExistsException("user not select group");
        }
        if (value) {
            user.getInfo().getRoles().add(Roles.VERIFIED);
            groupService.incrementCountOfAllUsersInGroup(user.getGroup().getId());
        } else {
            user.setGroup(null);
            user.getInfo().getRoles().remove(Roles.VERIFIED);
        }
        logger.info("request state changed");
    }

    @Transactional
    @Override
    public void selectGroup(long userId, long groupId) {
        if (groupRepository.exists(groupId)) {
            User user = userRepository.findOne(userId);
            user.setGroup(groupRepository.findOne(groupId));
            user.getInfo().getRoles().remove(Roles.VERIFIED);

        } else {
            logger.error("group not found");
            throw new NotFoundException("group not found");
        }

        logger.info("group selected");
    }

    @Transactional
    @Override
    public List<UserDto> getRequests() {
        logger.info("all users's requests found");
        return userConverter.convertToDto(userRepository.getRequests());
    }

    @Override
    @Transactional
    public void update(ProfileUserDto userDto) {
        if (userDto == null) {
            logger.error("user can't be null");
            throw new IllegalArgumentException("user can't be null");
        }
        if (userDto.getId() == null) {
            logger.error("user not found");
            throw new NotFoundException("user not found");
        }
        if (!userRepository.exists(userDto.getId())) {
            logger.error("user with id: {} not found", userDto.getId());
            throw new NotFoundException(String.format("user with id: %d not found", userDto.getId()));
        }

        UserInformation userInformation = userInformationRepository.findOne(userDto.getId());
        if (userInformationRepository.findByNickname(userDto.getInfo().getNickname()) != userInformation) {

            throw new ExistsException("user with this nickname exists");
        }
        if (userInformationRepository.findByEmail(userDto.getInfo().getEmail()) != userInformation) {
            throw new ExistsException("user with this email exists");
        }

        User user = userRepository.findOne(userDto.getId());

        user.setPatronymic(userDto.getPatronymic());
        user.setLastname(userDto.getLastname());
        user.setName(userDto.getName());

        userRepository.save(profileUserConverter.convertToEntity(userDto));
        logger.info("user with id: {} updated", userDto.getId());
    }

    @Override
    @Transactional
    public void removeAll() {
        userRepository.deleteAll();
        logger.info("all users deleted");
    }

    @Deprecated
    @Transactional
    @Override
    public List<UserDto> getVerifiedUsersInGroup(long id) {
        if (!groupRepository.exists(id)) {
            logger.error("group can't be null");
            throw new IllegalArgumentException("group can't be null");
        }

        logger.info("all users in group's id: {} found", id);
        return userConverter.convertToDto(userRepository.getVerifiedUsersInGroup(id));
    }

}
