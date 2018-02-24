package dev5.chermenin.service.impl;

import dev5.chermenin.service.TestDataBaseConfig;
import dev5.chermenin.service.api.GroupService;
import dev5.chermenin.service.dto.impl.GroupDto;
import dev5.chermenin.service.exceptions.ExistsException;
import dev5.chermenin.service.exceptions.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
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
@ComponentScan(basePackages = {"dev5.chermenin"})
public class GroupServiceTest{
    private final static Pageable pageable = new PageRequest(0,100);
    @Autowired
    private GroupService groupService;

    @Test
    public void findById() {
        assertEquals(groupService.findById(1L).getId(), (Long) 1L);
    }

    @Test(expected = NotFoundException.class)
    public void findByNotExistsId() {
        groupService.findById(100L);
    }

    @Test(expected = ExistsException.class)
    public void SaveGroupWithExistsInformation() {
        GroupDto dto = new GroupDto();
        dto.setInformation("group №3");

        groupService.save(dto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void SaveNullGroup() {
        groupService.save(null);
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

    @Test(expected = IllegalArgumentException.class)
    public void updateNullGroup() {
        groupService.update(null);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotExistsGroup() {
        GroupDto groupDto = new GroupDto();
        groupService.update(groupDto);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotExistsGroupWithId() {
        GroupDto groupDto = new GroupDto();
        groupDto.setId(100L);
        groupService.update(groupDto);
    }

    @Test()
    public void updateGroup() {
        GroupDto groupDto = groupService.findById(1L);
        groupDto.setInformation("newGroup#1");
        groupService.update(groupDto);
        assertEquals(groupService.findById(1L).getInformation(), groupDto.getInformation());
    }

    @Test(expected = NotFoundException.class)
    public void removeNotExistsGroup() {
        groupService.remove(1000L);
    }

    @Test
    public void findAll() {
        assertTrue(groupService.findAll(pageable).size() == 3);
    }

}
