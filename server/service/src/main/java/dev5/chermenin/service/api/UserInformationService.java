package dev5.chermenin.service.api;

import dev5.chermenin.service.dto.impl.user.UserInformationDto;

public interface UserInformationService {
    UserInformationDto findById(long id);

    UserInformationDto findByNickname(String nickname);

    UserInformationDto findByEmail(String email);

    void changePassword(long id, String password, String oldPassword);

    void changeEmail(long id, String email, String oldEmail);
}

