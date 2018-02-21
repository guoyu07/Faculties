package dev5.chermenin.service.impl;

import dev5.chermenin.dao.repository.SubjectRepository;
import dev5.chermenin.dao.repository.UserRepository;
import dev5.chermenin.service.TestDataBaseConfig;
import dev5.chermenin.service.api.SubjectService;
import dev5.chermenin.service.dto.impl.SubjectDto;
import dev5.chermenin.service.exceptions.ExistsException;
import dev5.chermenin.service.exceptions.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by Ancarian on 11.12.2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestDataBaseConfig.class)
public class SubjectServiceImplTest {
    private final static Pageable pageable = new PageRequest(0, 100);

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SubjectRepository subjectRepository;


    @Test
    public void findByExistId() throws Exception {
        SubjectDto subjectDto = subjectService.findById(2L);
        assertEquals(subjectDto.getSubject(), "math");
        assertEquals(subjectDto.getId(), (Long) 2L);

    }

    @Test(expected = NotFoundException.class)
    public void findByNotExistId() throws Exception {
        SubjectDto subjectDto = subjectService.findById(100L);
        assertNull(subjectDto);
    }

    @Test
    public void findByName() throws Exception {
        SubjectDto subjectDto = subjectService.findByName("math");
        assertEquals(subjectDto.getSubject(), "math");
        assertEquals(subjectDto.getId(), (Long) 2L);
    }

    @Test(expected = NotFoundException.class)
    public void findByNotExistName() throws Exception {
        subjectService.findByName("userrr");
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByIncorrectName() throws Exception {
        subjectService.findByName(null);
    }

    @Test
    public void saveSubjectDto() throws Exception {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setSubject("engl");
        subjectDto = subjectService.save(subjectDto);

        assertEquals(subjectDto.getId(), subjectService.findByName("engl").getId());
        subjectService.remove(subjectDto.getId());
    }

    @Test(expected = ExistsException.class)
    public void saveExistsSubjectDto() throws Exception {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setSubject("math");
        subjectService.save(subjectDto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveIncorrectSubjectDto() throws Exception {
        subjectService.save(null);
    }

    @Test(expected = NotFoundException.class)
    public void removeSubjectDto() throws Exception {
        subjectService.remove(100L);
    }

    @Test
    public void findAll() {
        assertEquals(subjectService.findAll(pageable).size(), 3);
    }

    @Transactional
    @Test
    public void addSubjectToUser() {
        subjectService.addSubjectToUser(13, 1, 100);
        assertTrue(userRepository.findOne(13L).getSubjects().containsKey(subjectRepository.findOne(1L)));
    }

    @Test(expected = NotFoundException.class)
    public void addNotExistsSubjectToUser() {
        subjectService.addSubjectToUser(13, 100, 100);
    }

    @Test(expected = NotFoundException.class)
    public void addSubjectToNotExistsUser() {
        subjectService.addSubjectToUser(130, 1, 100);
    }

    @Transactional
    @Test
    public void removeUserSubject() {
        subjectService.removeUserSubject(13, 3);
        assertTrue(!userRepository.findOne(13L).getSubjects().containsKey(subjectRepository.findOne(3L)));
    }

    @Test(expected = NotFoundException.class)
    public void removeNotExistsSubjectToUser() {
        subjectService.removeUserSubject(13, 100);
    }

    @Test(expected = NotFoundException.class)
    public void removeSubjectFromNotExistsUser() {
        subjectService.removeUserSubject(130, 1);
    }
}