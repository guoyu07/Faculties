package dev5.chermenin.service.impl;

import dev5.chermenin.service.TestDataBaseConfig;
import dev5.chermenin.service.api.GroupService;
import dev5.chermenin.service.api.UserService;
import dev5.chermenin.service.dto.impl.user.ProfileUserDto;
import dev5.chermenin.service.dto.impl.user.UserDto;
import dev5.chermenin.service.dto.impl.user.UserInformationDto;
import dev5.chermenin.service.exceptions.ExistsException;
import dev5.chermenin.service.exceptions.NotFoundException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by Ancarian on 22.10.2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestDataBaseConfig.class)
public class UserServiceImplTest {
    private final static Pageable pageable = new PageRequest(0, 100);

    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;

    private ProfileUserDto profileUserDto;

    @Before
    public void after() {
        this.profileUserDto = userService.findProfileById(1);
    }

    @Ignore
    @Test
    public void getUsersInGroup() throws Exception {
        List<UserDto> users = userService.getVerifiedUsersInGroup(1);
        assertEquals(users.size(), 6);

        List<UserDto> userss = userService.getVerifiedUsersInGroup(2);
        assertEquals(userss.size(), 7);
    }

    @Test
    public void findById() throws Exception {
        assertEquals((Long) 1L, userService.findById(1L).getId());
    }

    @Test(expected = NotFoundException.class)
    public void findByNonexistentId() throws Exception {
        userService.findById(1000);

    }

    @Test
    public void save() throws Exception {
        ProfileUserDto user = new ProfileUserDto();
        Map<String, Integer> subjects = new HashMap<>();
        subjects.put("certificate", 50);
        user.setMarks(subjects);

        UserInformationDto information = new UserInformationDto();
        information.setNickname("nickname_" + 18);
        user.setLastname("lastname");
        user.setPatronymic("patronymic");
        information.setEmail("email_" + 18);
        information.setPassword("password_" + 18);

        user.setName("username_" + 18);
        user.setInfo(information);
        user = userService.save(user);

        userService.remove(user.getId());
    }

    @Test
    public void update() {
        this.profileUserDto.setName("Ancarian");
        userService.update(profileUserDto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateNullUser() {
        userService.update(null);
    }

    @Test(expected = NotFoundException.class)
    public void updateWithNullId() {
        ProfileUserDto profileUserDto = userService.findProfileById(1);
        profileUserDto.setId(null);

        userService.update(profileUserDto);
    }

    @Test(expected = NotFoundException.class)
    public void updateWithNotExistsId() {
        ProfileUserDto profileUserDto = userService.findProfileById(1);
        profileUserDto.setId(1000L);

        userService.update(profileUserDto);
    }

    @Test(expected = ExistsException.class)
    public void updateExistNicnameUser() {

        this.profileUserDto.getInfo().setNickname("nickname_3");
        userService.update(profileUserDto);
    }

    @Test(expected = ExistsException.class)
    public void updateExistEmailUser() {
        this.profileUserDto.getInfo().setEmail("email_3");
        userService.update(profileUserDto);
    }

    @Ignore
    @Test
    public void remove() {
        userService.remove(1L);
        System.out.println(groupService.findAll(pageable).size());
    }

    @Test(expected = NotFoundException.class)
    public void removeNotExistUser() {
        userService.remove(100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveNull() throws Exception {
        userService.save(null);
    }

    @Test(expected = ExistsException.class)
    public void saveExistsEmail() {
        this.profileUserDto.getInfo().setNickname("nickname_" + 17);
        this.profileUserDto.setId(null);
        userService.save(this.profileUserDto);
    }

    @Test(expected = ExistsException.class)
    public void saveExistsNickname() {
        this.profileUserDto.getInfo().setEmail("email_" + 17);
        this.profileUserDto.setId(null);
        userService.save(profileUserDto);
    }

    @Test
    public void findAll() throws Exception {
        assertEquals(userService.findAll(pageable).size(), 17);
    }

    @Test
    public void getRequests() throws Exception {
        List<UserDto> users = userService.getRequests();
        assertEquals(users.size(), 3);
    }

    @Test
    public void changeStateOfRequestFalse() throws Exception {
        UserDto userDto = userService.findById(15);

        userService.selectGroup(userDto.getId(), 1);
        userService.changeStateOfRequest(userDto.getId(), false);

        userDto = userService.findById(15);
        assertEquals(userDto.getGroupId(), null);
        assertEquals(userService.getRequests().contains(userDto), false);
    }

    @Test
    public void changeStateOfRequestTrue() throws Exception {
        userService.selectGroup(15, 2);
        userService.changeStateOfRequest(15, true);
        UserDto userDto = userService.findById(15);

        Long answer = 2L;
        assertEquals(userDto.getGroupId(), answer);
        assertEquals(userService.getRequests().contains(userDto), false);

    }

    @Test(expected = ExistsException.class)
    public void changeStateOfRequestNotSelectGroup() throws Exception {
        userService.changeStateOfRequest(16, true);
    }

    @Test
    public void selectGroup() throws Exception {
        userService.selectGroup(15, 1);
        assertEquals(userService.findById(15).getGroupId(), (Long) 1L);
    }

    @Test(expected = NotFoundException.class)
    public void selectNotFoundGroup() throws Exception {
        userService.selectGroup(15, 100);
    }


}