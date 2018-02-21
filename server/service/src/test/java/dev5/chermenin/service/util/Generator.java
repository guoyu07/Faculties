package dev5.chermenin.service.util;

import dev5.chermenin.model.entity.impl.*;
import dev5.chermenin.model.entity.impl.enums.Roles;

import java.util.*;

/**
 * Created by Ancarian on 11.12.2017.
 */
public class Generator {
    public static Group generateGroup() {
        Group group = new Group();

        group.setId(1L);
        Set<Subject> subjects = new HashSet<>();
        subjects.add(generateSubject());
        group.setSubjects(subjects);
        group.setInformation("group №1");
        group.setIssueDate(java.sql.Date.valueOf("2017-12-12"));
        group.setValidTill(java.sql.Date.valueOf("2018-12-12"));
        group.setLimit(3);
        group.setUsers(new ArrayList<>());
        group.setCountOfAllUsers(10);
        group.setCountOfUsers(4);


        return group;
    }

    public static Subject generateSubject() {

        Subject subject = new Subject();
        subject.setId(1L);
        subject.setSubject("math");

        return subject;
    }

    public static Roles generateRoles() {
        return Roles.USER;
    }

    public static UserInformation generateUserInfo() {

        UserInformation userInformation = new UserInformation();

        userInformation.setPassword("password");
        userInformation.setEmail("abc@gmail.com");
        userInformation.setId(1L);

        Set<Roles> roles = new HashSet<>();
        roles.add(generateRoles());

        userInformation.setRoles(roles);
        return userInformation;
    }

    public static User generateUser() {
        User user = new User();

        Map<Subject, Integer> marks = new HashMap<>();
        marks.put(generateSubject(), 50);

        user.setGroup(generateGroup());
        user.setSubjects(marks);
        user.setInfo(generateUserInfo());

        user.setName("name");
        user.setLastname("lastname");
        user.setPatronymic("patronymic");

        user.setId(1L);

        return user;
    }


}
