//package dev5.chermenin.service.impl;
//
//import dev5.chermenin.service.dto.impl.RoleDto;
//import dev5.chermenin.service.TestDataBaseConfig;
//import dev5.chermenin.service.api.RoleService;
//import dev5.chermenin.service.exceptions.ExistsException;
//import dev5.chermenin.service.exceptions.NotFoundException;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
///**
// * Created by Ancarian on 11.12.2017.
// */
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = TestDataBaseConfig.class)
//public class RoleServiceImplTest {
//    @Autowired
//    private RoleService roleService;
//
//    @Test
//    public void findByExistId() throws Exception {
//        RoleDto roleDto = roleService.findById(2);
//        assertEquals(roleDto.getRole(), "USER");
//        assertEquals(roleDto.getId(), (Long) 2L);
//
//    }
//
//    @Test(expected = NotFoundException.class)
//    public void findByNotExistId() throws Exception {
//        roleService.findById(100);
//    }
//
//    @Test
//    public void findByName() throws Exception {
//        RoleDto roleDto = roleService.findByName("USER");
//        assertEquals(roleDto.getRole(), "USER");
//        assertEquals(roleDto.getId(), (Long) 2L);
//    }
//
//    @Test(expected = NotFoundException.class)
//    public void findByNotExistName() throws Exception {
//        roleService.findByName("userrr");
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void findByIncorrectName() throws Exception {
//        roleService.findByName(null);
//    }
//
//    @Test
//    public void saveRoleDto() throws Exception {
//        RoleDto roleDto = new RoleDto();
//        roleDto.setRole("moderator");
//        roleDto.setId(4L);
//        roleDto = roleService.save(roleDto);
//
//        assertEquals(roleDto.getRole(), roleService.findByName("moderator").getRole());
//        roleService.remove(roleService.findByName("moderator").getId());
//    }
//    @Test(expected = ExistsException.class)
//    public void saveExistsRoleDto(){
//        RoleDto roleDto = new RoleDto();
//        roleDto.setRole("USER");
//        roleService.save(roleDto);
//    }
//    @Test(expected = IllegalArgumentException.class)
//    public void saveIncorrectRoleDto() throws Exception {
//        roleService.save(null);
//    }
//    @Test(expected = NotFoundException.class)
//    public void removeRoleDto() throws Exception {
//        roleService.remove(6L);
//    }
//    @Test
//    public void findAll(){
//        assertEquals(roleService.findAll().size(), 3);
//    }
//    @Test
//    public void addRoleToUser(){
//        roleService.addRoleToUser(1, 1);
//        assertTrue(roleService.getRolesByUser(1).contains(roleService.findById(1)));
//    }
//    @Test(expected = NotFoundException.class)
//    public void addNotExistsRoleToUser(){
//        roleService.addRoleToUser(1, 100);
//    }
//    @Test(expected = NotFoundException.class)
//    public void addRoleToNotExistsUser(){
//        roleService.addRoleToUser(100, 1);
//    }
//    @Test
//    public void removeUserRole(){
//        roleService.removeUserRole(1, 1);
//        assertTrue(!roleService.getRolesByUser(1).contains(roleService.findById(1)));
//    }
//    @Test(expected = NotFoundException.class)
//    public void removeUserNotExistsRole(){
//        roleService.removeUserRole(1, 100);
//    }
//    @Test(expected = NotFoundException.class)
//    public void removeNotExistsUserRole(){
//        roleService.removeUserRole(100, 1);
//    }
//    @Test(expected = NotFoundException.class)
//    public void getNotExistsUserRoles() {
//
//        roleService.getRolesByUser(100);
//    }
//
//}