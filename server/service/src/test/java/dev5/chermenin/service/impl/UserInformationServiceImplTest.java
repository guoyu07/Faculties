package dev5.chermenin.service.impl;

import dev5.chermenin.service.TestDataBaseConfig;
import dev5.chermenin.service.api.UserInformationService;
import dev5.chermenin.service.api.UserService;
import dev5.chermenin.service.exceptions.ConflictException;
import dev5.chermenin.service.exceptions.ExistsException;
import dev5.chermenin.service.exceptions.NotFoundException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Ancarian on 22.10.2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestDataBaseConfig.class)
public class UserInformationServiceImplTest {
    @Autowired
    private UserInformationService infoService;
    @Autowired
    private UserService service;

    @Test
    public void findById() throws Exception {
        assertEquals(infoService.findById(1).getId(), new Long(1));
    }

    @Test(expected = NotFoundException.class)
    public void findByNotExistsId() throws Exception {
        infoService.findById(1000);
    }

    @Test
    public void findByNickname() throws Exception {
        assertEquals(infoService.findByNickname("nickname_1").getNickname(), "nickname_1");
    }

    @Test(expected = NotFoundException.class)
    public void findByNonexistentNickname() throws Exception {
        infoService.findByNickname("NotExistNick");
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByNullNickname() throws Exception {
        assertEquals(infoService.findByNickname(null), null);
    }

    @Test
    public void findByEmail() throws Exception {
        assertEquals(infoService.findByEmail("email_1").getEmail(), "email_1");
    }

    @Test(expected = NotFoundException.class)
    public void findByNonexistentEmail() throws Exception {
        assertEquals(infoService.findByEmail("NotExistEmail"), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByNullEmail() throws Exception {
        assertEquals(infoService.findByEmail(null), null);
    }
    @Test(expected = NotFoundException.class)
    public void changeEmailNotExistUserInformation(){

        infoService.changeEmail(1000, "oldEmail@gmail.com", "newEmail@gmail.com");
    }
    @Test(expected = ExistsException.class)
    public void changeExistsEmailUserInformation(){

        infoService.changeEmail(1, "oldEmail@gmail.com", "email_1");
    }
    @Test(expected = ConflictException.class)
    public void changeInvalidEmailUserInformation(){
        infoService.changeEmail(2, "invalidOldEmail@gmail.com", "newEmail@gmail.com");
    }
    @Test
    public void changeEmailUserInformation(){
        infoService.changeEmail(2, "oldEmail@gmail.com", "newEmail@gmail.com");
        assertEquals(infoService.findById(2).getEmail(), "newEmail@gmail.com");
    }

    @Test(expected = NotFoundException.class)
    public void changePasswordNotExistUserInformation(){

        infoService.changePassword(1000, "oldPassword", "newPassword");
    }
    @Test(expected = ConflictException.class)
    public void changeInvalidPasswordUserInformation(){
        infoService.changePassword(2, "invalidOldPassword", "newPassword");
    }
    @Test
    public void changePasswordUserInformation(){
        infoService.changePassword(2, "oldPassword", "newPassword");
        assertEquals(infoService.findById(2).getPassword(), "newPassword");
    }

}