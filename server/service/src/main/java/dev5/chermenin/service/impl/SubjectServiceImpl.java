package dev5.chermenin.service.impl;

import dev5.chermenin.dao.repository.SubjectRepository;
import dev5.chermenin.dao.repository.UserRepository;
import dev5.chermenin.model.entity.impl.Subject;
import dev5.chermenin.model.entity.impl.User;
import dev5.chermenin.service.api.SubjectService;
import dev5.chermenin.service.dto.impl.SubjectDto;
import dev5.chermenin.service.exceptions.ExistsException;
import dev5.chermenin.service.exceptions.NotFoundException;
import dev5.chermenin.service.util.converters.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ancarian on 01.12.2017.
 */
@Service
public class SubjectServiceImpl implements SubjectService {

    private SubjectRepository subjectRepository;
    private UserRepository userRepository;
    private Converter<SubjectDto, Subject> subjectConverter;
    private Logger logger = LoggerFactory.getLogger(SubjectServiceImpl.class);

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository, UserRepository userRepository, Converter<SubjectDto, Subject> subjectConverter) {
        this.subjectRepository = subjectRepository;
        this.subjectConverter = subjectConverter;
        this.userRepository = userRepository;
    }

    @Override
    public SubjectDto findByName(String name) {
        if (name == null) {
            logger.error("subject's name can't be null");
            throw new IllegalArgumentException("subject's name can't be null");
        }

        Subject subject = subjectRepository.findByName(name);
        logger.info("subject with name: {} {}", name, subject == null ? "not found" : "found");
        if (subject == null) {
            logger.error("subject with name: {}", name, "not found");
            throw new NotFoundException(String.format("subject with id: %s not found", name));
        }

        return subjectConverter.convertToDto(subject);
    }

    @Override
    public SubjectDto findById(Long id) {
        if (!subjectRepository.exists(id)) {
            logger.error("subject with name: {}", id, "not found");
            throw new NotFoundException(String.format("subject with id: %d not found", id));
        }

        Subject subject = subjectRepository.findOne(id);
        return subjectConverter.convertToDto(subject);
    }

    @Override
    @Transactional
    public SubjectDto save(SubjectDto subjectDto) {
        if (subjectDto == null) {
            logger.error("subject can't be null");
            throw new IllegalArgumentException("subject can't be null");
        }

        if (subjectRepository.findByName(subjectDto.getSubject()) != null) {
            throw new ExistsException("subject exists");
        }

        Subject subject = new Subject();
        subject.setSubject(subjectDto.getSubject());
        logger.info("subject with id: {} saved", subject.getId());
        return subjectConverter.convertToDto(subjectRepository.save(subject));
    }

    @Override
    @Transactional
    public void remove(Long id) {
        if (subjectRepository.exists(id)) {
            subjectRepository.delete(id);
            logger.info("subject with id: {} deleted", id);
        } else {
            logger.error("subject with id: {}", id, "not found");
            throw new NotFoundException(String.format("subject with id: %d not found", id));
        }
    }

    @Override
    @Transactional
    public void removeAll() {
        subjectRepository.deleteAll();
        logger.info("all subjects deleted");
    }

    @Override
    public List<SubjectDto> findAll(Pageable pageable) {
        return subjectConverter.convertToDto(subjectRepository.findAll(pageable).getContent());
    }

    @Transactional
    @Override
    public void addSubjectToUser(long userId, long subjectId, int mark) {
        if (!userRepository.exists(userId)) {
            logger.error("user with id: {} not found", userId);
            throw new NotFoundException(String.format("user with id: %d not found", userId));

        }
        if (!subjectRepository.exists(subjectId)) {
            logger.error("role with id: {} not found", subjectId);
            throw new NotFoundException(String.format("role with id: %d not found", subjectId));
        }

        User user = userRepository.findOne(userId);
        user.getSubjects().put(subjectRepository.findOne(subjectId), mark);

    }

    @Transactional
    @Override
    public void removeUserSubject(long userId, long subjectId) {
        if (!userRepository.exists(userId)) {
            logger.error("user with id: {} not found", userId);
            throw new NotFoundException(String.format("user with id: %d not found", userId));

        }
        if (!subjectRepository.exists(subjectId)) {
            logger.error("subject with id: {} not found", subjectId);
            throw new NotFoundException(String.format("subject with id: %d not found", subjectId));
        }

        User user = userRepository.findOne(userId);
        user.getSubjects().remove(subjectRepository.findOne(subjectId));
    }

    @Override
    public void update(SubjectDto id) {

    }
}
