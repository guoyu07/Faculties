package dev5.chermenin.service.impl;

import dev5.chermenin.dao.repository.FacultyRepository;
import dev5.chermenin.dao.repository.GroupRepository;
import dev5.chermenin.dao.repository.UserRepository;
import dev5.chermenin.model.entity.impl.Group;
import dev5.chermenin.service.api.FacultyService;
import dev5.chermenin.service.api.GroupService;
import dev5.chermenin.service.dto.impl.GroupDto;
import dev5.chermenin.service.exceptions.ExistsException;
import dev5.chermenin.service.exceptions.NotFoundException;
import dev5.chermenin.service.util.converters.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    private UserRepository userRepository;
    private GroupRepository groupRepository;
    private FacultyRepository facultyRepository;
    private Converter<GroupDto, Group> groupConverter;
    private Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);

    @Autowired
    public GroupServiceImpl(UserRepository userRepository, GroupRepository groupRepository, FacultyRepository facultyRepository, Converter<GroupDto, Group> groupConverter) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.facultyRepository = facultyRepository;
        this.groupConverter = groupConverter;
    }

    @Transactional
    public GroupDto findById(Long id) {
        if (!this.groupRepository.exists(id)) {
            this.logger.error("group with id: {} not found", id);
            throw new NotFoundException(String.format("group with id: %d not found", id));
        }
        Group group = this.groupRepository.findOne(id);
        group.setUsers(this.userRepository.getVerifiedUsersInGroup(id));
        this.logger.info("group with id: {} found", id);
        return this.groupConverter.convertToDto(group);
    }

    @Transactional
    public GroupDto save(GroupDto groupDto) {
        if (groupDto == null) {
            this.logger.error("group can't be null");
            throw new IllegalArgumentException("group can't be null");
        }
        if (this.groupRepository.findByInformation(groupDto.getInformation()) != null) {
            throw new ExistsException("group exists");
        }

        groupDto.setId(null);
        Group group = new Group();
        group.setInformation(groupDto.getInformation());
        group.setLimit(groupDto.getLimit());
        group.setValidTill(groupDto.getValidTill());
        group.setIssueDate(groupDto.getIssueDate());
        group.setSubjects(null);
        group.setUsers(null);
        group.setQualify(groupDto.getQualify());
        group.setFaculty(facultyRepository.findOne(groupDto.getFacultyId()));

        groupDto = this.groupConverter.convertToDto(this.groupRepository.save(group));
        this.logger.info("group with id: {} saved", groupDto.getId());
        return groupDto;
    }

    @Transactional
    public void remove(Long id) {
        if (!this.groupRepository.exists(id)) {
            this.logger.error("group with id: {} not found", id);
            throw new NotFoundException(String.format("group with id: %d not found", id));
        }
        this.groupRepository.delete(id);
        this.logger.info("group with id: {} deleted", id);
    }

    @Transactional
    public void update(GroupDto groupDto) {
        if (groupDto == null) {
            this.logger.error("group can't be null");
            throw new IllegalArgumentException("group can't be null");
        }
        if (groupDto.getId() == null) {
            this.logger.error("group not found", groupDto.getId());
            throw new NotFoundException("group not found");
        }
        if (!this.groupRepository.exists(groupDto.getId())) {
            this.logger.error("group with id: {} not found", groupDto.getId());
            throw new NotFoundException(String.format("group with id: %d not found", groupDto.getId()));
        }

        Group group = this.groupRepository.findOne(groupDto.getId());
        group.setInformation(groupDto.getInformation());
        group.setLimit(groupDto.getLimit());
        group.setValidTill(groupDto.getValidTill());
        group.setIssueDate(groupDto.getIssueDate());

        this.groupRepository.save(group);
        this.logger.info("group with id: {} updated", groupDto.getId());
    }

    @Transactional(readOnly = true)
    public List<GroupDto> findAll(Pageable pageable) {
        List<Group> groups = this.groupRepository.findAll(pageable).getContent();
        this.logger.info("all groups found");

        groups.forEach(group -> group.setUsers(null));
        return this.groupConverter.convertToDto(groups);
    }

    @Transactional
    public void removeAll() {
        this.groupRepository.deleteAll();
        this.logger.info("all groups deleted");
    }

    public void incrementCountOfAllUsersInGroup(long id) {
        if (!this.groupRepository.exists(id)) {
            this.logger.error("group with id: {} not found", id);
            throw new NotFoundException(String.format("group with id: %d not found", id));
        }
        Group group = this.groupRepository.findOne(id);
        group.setCountOfUsers(group.getCountOfAllUsers() + 1);
        this.groupRepository.save(group);
    }

    @Transactional
    public void calculatePassMark(long id) {
        if (!this.groupRepository.exists(id)) {
            this.logger.error("group with id: {} not found", id);
            throw new NotFoundException(String.format("group with id: %d not found", id));
        }
        Group group = this.groupRepository.findOne(id);
        Integer mark = this.groupRepository.getEnrollMark(id);
        if (mark == null) {
            mark = 0;
        }
        group.setEnrollMark(mark);
    }

    @Transactional
    public List<GroupDto> getGroupsByQualifyLike(String pattern) {
        List<Group> groups = this.groupRepository.findAll();
        this.logger.info("all groups found");
        groups.forEach(group -> group.setUsers(null));
        return this.groupConverter.convertToDto(groups);
    }
}

