package dev5.chermenin.service.impl;

import dev5.chermenin.dao.repository.UserInformationRepository;
import dev5.chermenin.model.entity.impl.UserInformation;
import dev5.chermenin.service.api.UserInformationService;
import dev5.chermenin.service.dto.impl.user.UserInformationDto;
import dev5.chermenin.service.exceptions.ConflictException;
import dev5.chermenin.service.exceptions.ExistsException;
import dev5.chermenin.service.exceptions.NotFoundException;
import dev5.chermenin.service.util.converters.Converter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * Created by Ancarian on 10.10.2017.
 */
@Service
@RequiredArgsConstructor
public class UserInformationServiceImpl implements UserInformationService {
    private final UserInformationRepository userInformationRepository;
    private final Converter<UserInformationDto, UserInformation> userInformationConverter;
    private final Logger logger = LoggerFactory.getLogger(UserInformationServiceImpl.class);

    @Override
    public UserInformationDto findById(Long id) {
        return userInformationConverter.convertToDto(userInformationRepository.findOne(id));
    }

    @Override
    public UserInformationDto findByNickname(String nickname) {
        UserInformation userInformation = userInformationRepository.findByNickname(nickname);
        return userInformationConverter.convertToDto(userInformation);
    }

    @Override
    public UserInformationDto findByEmail(String email) {
        UserInformation userInformation = userInformationRepository.findByEmail(email);
        return userInformationConverter.convertToDto(userInformation);
    }

    @Transactional
    @Override
    public void changePassword(Long userId, String oldPassword, String newPassword) {
        UserInformation userInformation = userInformationRepository.findOne(userId);
        if (!Objects.equals(userInformation.getPassword(), oldPassword)) {
            throw new ConflictException("user's information old password is invalid");
        }
        userInformation.setPassword(newPassword);
    }

    @Transactional
    @Override
    public void changeEmail(Long userId, String oldEmail, String newEmail) {
        if (userInformationRepository.findByEmail(newEmail) != null) {
            throw new ExistsException("user's information with this email already exists");
        }
        UserInformation userInformation = userInformationRepository.findOne(userId);
        if (!Objects.equals(userInformation.getEmail(), oldEmail)) {
            throw new ConflictException("user's information old email is invalid");
        }
        userInformation.setEmail(newEmail);
    }


    @Transactional
    @Override
    public UserInformation findUserInfoById(Long id) {
        UserInformation userInformation = userInformationRepository.findOne(id);
        System.out.println(userInformation.getRoles());
        return userInformation;
    }
}
