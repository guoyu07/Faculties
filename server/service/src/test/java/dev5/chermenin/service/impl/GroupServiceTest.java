package dev5.chermenin.service.impl;

import dev5.chermenin.dao.repository.GroupRepository;
import dev5.chermenin.service.TestDataBaseConfig;
import dev5.chermenin.service.api.GroupService;
import dev5.chermenin.service.api.UserService;
import dev5.chermenin.service.dto.impl.GroupDto;
import dev5.chermenin.service.exceptions.ExistsException;
import dev5.chermenin.service.exceptions.NotFoundException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Ancarian on 10.11.2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestDataBaseConfig.class)
public class GroupServiceTest{
    private final static Pageable pageable = new PageRequest(0,100);
    @Autowired
    private GroupService groupService;

    @Test
    public void findById() {
        Long value = 1L;
        assertEquals(groupService.findById(1L).getId(), value);
    }

    @Test
    public void saveGroup() {
        GroupDto dto = new GroupDto();
        dto.setInformation("group №4");
        dto.setUsers(new ArrayList<>());
        dto.setSubjectNames(new HashSet<>());
        dto.setFacultyId(1L);
        dto.setQualify("builder");
        groupService.save(dto);
        dto.setId(4L);
        assertEquals(dto.getInformation(), groupService.findById(4L).getInformation());

        groupService.remove(4L);
    }

    @Test()
    public void updateGroup() {
        GroupDto groupDto = groupService.findById(1L);
        groupDto.setInformation("newGroup#1");
        groupService.update(groupDto);
        assertEquals(groupService.findById(1L).getInformation(), groupDto.getInformation());
    }

    @Ignore
    @Test
    public void removeGroup() {
        groupService.remove(1L);
    }

    @Test
    public void findAll() {
        assertTrue(groupService.findAll(pageable).size() == 3);
    }

}
