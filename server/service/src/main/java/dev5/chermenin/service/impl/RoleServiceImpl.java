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

import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final UserRepository userRepository;

    @Transactional
    public void addRoleToUser(long userId, Roles role) {
        this.userRepository.findOne(userId).getInfo().getRoles().add(role);
    }

    @Transactional
    public void removeUserRole(long userId, Roles role) {
        this.userRepository.findOne(userId).getInfo().getRoles().remove(role);
    }

    @Transactional
    @Override
    public Set<Roles> getRolesByUserId(Long userId) {
        return this.userRepository.findOne(userId).getInfo().getRoles();
    }
}

