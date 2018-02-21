package dev5.chermenin.service.api;

import dev5.chermenin.service.dto.impl.user.ProfileUserDto;
import dev5.chermenin.service.dto.impl.user.UserDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    UserDto findById(long id);

    ProfileUserDto findProfileById(long id);

    ProfileUserDto save(ProfileUserDto profileUserDto);

    List<UserDto> findAll(Pageable pageable);

    void remove(long id);

    List<UserDto> getRequests();

    void update(ProfileUserDto profileUserDto);

    void removeAll();

    List<UserDto> getVerifiedUsersInGroup(long id);

    void selectGroup(long userId, long groupId);

    void changeStateOfRequest(long userId, boolean value);
}

