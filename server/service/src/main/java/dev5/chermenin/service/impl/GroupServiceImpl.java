package dev5.chermenin.service.impl;

import dev5.chermenin.dao.repository.FacultyRepository;
import dev5.chermenin.dao.repository.GroupRepository;
import dev5.chermenin.dao.repository.UserRepository;
import dev5.chermenin.model.entity.impl.Group;
import dev5.chermenin.service.api.GroupService;
import dev5.chermenin.service.dto.impl.GroupDto;
import dev5.chermenin.service.exceptions.ExistsException;
import dev5.chermenin.service.util.converters.Converter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final FacultyRepository facultyRepository;
    private final Converter<GroupDto, Group> groupConverter;

    @Transactional(readOnly = true)
    public GroupDto findById(Long id) {
        Group group = groupRepository.findOne(id);
        GroupDto groupDto = groupConverter.convertToDto(group);
        groupDto.setUsers(userRepository.getVerifiedUsersInGroup(id));
        return groupDto;
    }

    @Transactional
    public GroupDto save(GroupDto groupDto) {
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

        groupDto = groupConverter.convertToDto(groupRepository.save(group));
        return groupDto;
    }

    @Transactional
    public void remove(Long id) {
        groupRepository.delete(id);
    }

    @Transactional
    public void update(GroupDto groupDto) {
        Group group = groupRepository.findOne(groupDto.getId());
        group.setInformation(groupDto.getInformation());
        group.setLimit(groupDto.getLimit());
        group.setValidTill(groupDto.getValidTill());
        group.setIssueDate(groupDto.getIssueDate());

        groupRepository.save(group);
    }

    @Transactional(readOnly = true)
    public List<GroupDto> findAll(Pageable pageable) {
        List<Group> groups = groupRepository.findAll(pageable).getContent();
        groups.forEach(group -> group.setUsers(null));
        return this.groupConverter.convertToDto(groups);
    }

    @Transactional
    public void removeAll() {
        groupRepository.deleteAll();
    }
}

