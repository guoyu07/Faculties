package dev5.chermenin.service.impl;


import dev5.chermenin.dao.repository.GroupRepository;
import dev5.chermenin.dao.repository.UserInformationRepository;
import dev5.chermenin.dao.repository.UserRepository;
import dev5.chermenin.model.entity.impl.Group;
import dev5.chermenin.model.entity.impl.User;
import dev5.chermenin.model.entity.impl.UserInformation;
import dev5.chermenin.model.entity.impl.enums.Roles;
import dev5.chermenin.service.api.UserService;
import dev5.chermenin.service.dto.impl.user.ProfileUserDto;
import dev5.chermenin.service.dto.impl.user.RegisterDto;
import dev5.chermenin.service.dto.impl.user.UserDto;
import dev5.chermenin.service.exceptions.ConflictException;
import dev5.chermenin.service.exceptions.ExistsException;
import dev5.chermenin.service.exceptions.NotFoundException;
import dev5.chermenin.service.util.converters.Converter;
import dev5.chermenin.service.util.converters.impl.ProfileUserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.util.Password;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Ancarian on 10.10.2017.
 */

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final UserInformationRepository userInformationRepository;
    private final Converter<UserDto, User> userConverter;
    private final ProfileUserConverter profileUserConverter;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserDto findById(long id) {
        User user = userRepository.findOne(id);
        return userConverter.convertToDto(user);
    }

    @Transactional
    @Override
    public ProfileUserDto findProfileById(long id) {
        User user = userRepository.findOne(id);
        return profileUserConverter.convertToDto(user);
    }

    @Override
    @Transactional
    public RegisterDto save(RegisterDto userDto) {
        if (userInformationRepository.findByEmail(userDto.getEmail()) != null) {
            throw new ExistsException("user with this email exists");
        }
        if (userInformationRepository.findByNickname(userDto.getNickname()) != null) {
            throw new ExistsException("user with this nickname exists");
        }

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = new User();

        Set<Roles> roles = new HashSet<>();
        user.setInfo(new UserInformation());
        roles.add(Roles.USER);
        user.getInfo().setRoles(roles);
        user.setName(userDto.getName());
        user.setLastname(userDto.getLastname());
        user.setPatronymic(userDto.getPatronymic());
        user.getInfo().setEmail(userDto.getEmail());
        user.getInfo().setPassword(userDto.getPassword());
        user.getInfo().setNickname(userDto.getNickname());
        userRepository.save(user);
        userDto.setId(user.getId());
        return userDto;
    }

    @Transactional
    @Override
    public List<UserDto> findAll(Pageable pageable) {
        List<User> users = userRepository.findAll(pageable).getContent();
        return userConverter.convertToDto(users);
    }

    @Override
    @Transactional
    public void remove(long id) {
        if (userRepository.exists(id)) {
            userRepository.delete(id);
        } else {
            throw new NotFoundException(String.format("user with id: %d not found", id));
        }
    }

    @Transactional
    public void changeStateOfRequest(long userId, boolean value) {
        User user = userRepository.findOne(userId);

        if (value) {
            if (user.getGroup() == null) {
                throw new ExistsException("user not select group");
            }
            user.getInfo().getRoles().add(Roles.VERIFIED);
        } else {
            user.setGroup(null);
            user.getInfo().getRoles().remove(Roles.VERIFIED);
        }
    }

    @Transactional
    @Override
    public void selectGroup(long userId, long groupId) {
        Group group = groupRepository.findOne(groupId);
        User user = userRepository.findOne(userId);
        if (!group.getSubjects().equals(user.getSubjects().keySet())) {
            throw new ConflictException("group subjects not equals your subjects");
        }
        user.setGroup(group);
        user.getInfo().getRoles().remove(Roles.VERIFIED);
    }


    @Transactional
    @Override
    public List<UserDto> getRequests() {
        return userConverter.convertToDto(userRepository.getRequests());
    }

    @Override
    @Transactional
    public void update(ProfileUserDto userDto) {
        User user = userRepository.findOne(userDto.getId());

        user.setPatronymic(userDto.getPatronymic());
        user.setLastname(userDto.getLastname());
        user.setName(userDto.getName());
    }

    @Override
    @Transactional
    public void removeAll() {
        userRepository.deleteAll();
    }


}
