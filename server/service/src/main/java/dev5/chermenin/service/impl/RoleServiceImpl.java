package dev5.chermenin.service.impl;

import dev5.chermenin.dao.repository.UserRepository;
import dev5.chermenin.model.entity.impl.User;
import dev5.chermenin.model.entity.impl.enums.Roles;
import dev5.chermenin.service.api.RoleService;
import dev5.chermenin.service.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Transactional
    public void addRoleToUser(long userId, Roles role) {
        if (!this.userRepository.exists(userId)) {
            this.logger.error("user with id: {} not found", userId);
            throw new NotFoundException(String.format("user with id: %d not found", userId));
        }
        User user = this.userRepository.findOne((userId));
        user.getInfo().getRoles().add(role);
    }

    @Transactional
    public void removeUserRole(long userId, Roles role) {
        if (!this.userRepository.exists(userId)) {
            this.logger.error("user with id: {} not found", userId);
            throw new NotFoundException(String.format("user with id: %d not found", userId));
        }
        User user = this.userRepository.findOne(userId);
        user.getInfo().getRoles().remove(role);
    }

    @Transactional
    @Override
    public Set<Roles> getRolesByUserId(Long userId) {
        if (!this.userRepository.exists(userId)) {
            this.logger.error("user with id: {} not found", userId);
            throw new NotFoundException(String.format("user with id: %d not found", userId));
        }
        return userRepository.findOne(userId).getInfo().getRoles();
    }
}

