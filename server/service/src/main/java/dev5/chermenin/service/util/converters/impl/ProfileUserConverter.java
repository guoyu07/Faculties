package dev5.chermenin.service.util.converters.impl;

import dev5.chermenin.dao.repository.UserRepository;
import dev5.chermenin.model.entity.impl.User;
import dev5.chermenin.service.dto.impl.user.ProfileUserDto;
import dev5.chermenin.service.util.converters.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component(value = "profileUserConverter")
public class ProfileUserConverter extends AbstractConverter<ProfileUserDto, User> {
    private ModelMapper modelMapper;
    private UserRepository userRepository;

    @Autowired
    public ProfileUserConverter(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public ProfileUserDto convertToDto(User user) {
        ProfileUserDto dto = this.modelMapper.map(user, ProfileUserDto.class);
        if (user.getGroup() != null) {
            dto.setGroupId(user.getGroup().getId());
        }
        if (user.getSubjects() != null) {
            dto.setMarks(user.getSubjects().entrySet().stream().collect(Collectors.toMap(e -> (e.getKey()).getSubject(), Map.Entry::getValue)));
        }
        return dto;
    }

    public User convertToEntity(ProfileUserDto userDto) {
        User oldUser;
        User user = (User) this.modelMapper.map((Object) userDto, User.class);
        if (userDto.getId() != null && (oldUser = (User) this.userRepository.findOne(user.getId())) != null) {
            user.setSubjects(oldUser.getSubjects());
            user.getInfo().setRoles(oldUser.getInfo().getRoles());
            user.setGroup(oldUser.getGroup());
        }
        return user;
    }
}

