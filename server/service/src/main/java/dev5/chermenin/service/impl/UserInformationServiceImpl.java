package dev5.chermenin.service.impl;

import dev5.chermenin.dao.repository.UserInformationRepository;
import dev5.chermenin.model.entity.impl.UserInformation;
import dev5.chermenin.service.api.UserInformationService;
import dev5.chermenin.service.dto.impl.user.UserInformationDto;
import dev5.chermenin.service.exceptions.ConflictException;
import dev5.chermenin.service.exceptions.ExistsException;
import dev5.chermenin.service.exceptions.NotFoundException;
import dev5.chermenin.service.util.converters.Converter;
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
public class UserInformationServiceImpl implements UserInformationService {
    private UserInformationRepository userInformationRepository;
    private Converter<UserInformationDto, UserInformation> userInformationConverter;
    private Logger logger = LoggerFactory.getLogger(UserInformationServiceImpl.class);

    @Autowired
    public UserInformationServiceImpl(UserInformationRepository userInformationRepository, Converter<UserInformationDto, UserInformation> userInformationConverter) {
        this.userInformationRepository = userInformationRepository;
        this.userInformationConverter = userInformationConverter;
    }

    @Override
    public UserInformationDto findById(Long id) {
        if (!userInformationRepository.exists(id)) {
            logger.error("user's information not found");
            throw new NotFoundException("user's information not found");
        }

        return userInformationConverter.convertToDto(userInformationRepository.findOne(id));
    }

    @Override
    public UserInformationDto findByNickname(String nickname) {
        if (nickname == null) {
            logger.error("nickname can't be null");
            throw new IllegalArgumentException("nickname can't be null");
        }

        UserInformation userInformation = userInformationRepository.findByNickname(nickname);
        if (userInformation == null) {
            logger.error("user's information not found");
            throw new NotFoundException("user's information not found");
        }

        logger.info("get UserInformation with nickname: {}", nickname);
        return userInformationConverter.convertToDto(userInformation);
    }

    @Override
    public UserInformationDto findByEmail(String email) {
        if (email == null) {
            logger.error("email can't be null");
            throw new IllegalArgumentException("email can't be null");
        }

        UserInformation userInformation = userInformationRepository.findByEmail(email);
        if (userInformation == null) {
            logger.error("user's information not found");
            throw new NotFoundException("user's information not found");
        }
        logger.info("get UserInformation with email: {}", email);
        return userInformationConverter.convertToDto(userInformation);
    }

    @Transactional
    @Override
    public void changePassword(Long userId, String oldPassword, String newPassword) {
        if (!userInformationRepository.exists(userId)) {
            logger.error("user's information not found");
            throw new NotFoundException("user's information not found");
        }

        UserInformation userInformation = userInformationRepository.findOne(userId);
        if (!Objects.equals(userInformation.getPassword(), oldPassword)) {
            logger.error("user's information old password is invalid");
            throw new ConflictException("user's information old password is invalid");
        }
        userInformation.setPassword(newPassword);
    }

    @Transactional
    @Override
    public void changeEmail(Long userId, String oldEmail, String newEmail) {
        if (!userInformationRepository.exists(userId)) {
            logger.error("user's information not found");
            throw new NotFoundException("user's information not found");
        }

        if (userInformationRepository.findByEmail(newEmail) != null) {
            logger.error("user's information with this email already exists");
            throw new ExistsException("user's information with this email already exists");
        }

        UserInformation userInformation = userInformationRepository.findOne(userId);

        if (!Objects.equals(userInformation.getEmail(), oldEmail)) {
            logger.error("user's information old email is invalid");
            throw new ConflictException("user's information old email is invalid");
        }

        userInformation.setEmail(newEmail);
    }

    @Transactional(readOnly = true)
    @Override
    public UserInformation findUserInfoById(Long id) {
        UserInformation userInformation = userInformationRepository.findOne(id);
        System.out.println(userInformation.getRoles());
        return userInformation;
    }
}
