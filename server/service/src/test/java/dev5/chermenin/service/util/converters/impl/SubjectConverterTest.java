package dev5.chermenin.service.util.converters.impl;

import dev5.chermenin.service.dto.impl.SubjectDto;
import dev5.chermenin.model.entity.impl.Subject;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import static dev5.chermenin.service.util.Generator.generateSubject;
import static org.junit.Assert.assertEquals;

/**
 * Created by Ancarian on 11.12.2017.
 */
public class SubjectConverterTest {

    private ModelMapper modelMapper = new ModelMapper();
    private SubjectConverter subjectConverter;
    private Subject subject;

    @Before
    public void setUp() throws Exception {
        subject = generateSubject();

        subjectConverter = new SubjectConverter(modelMapper);
    }

    @Test
    public void convertToDto() throws Exception {
        SubjectDto dto = subjectConverter.convertToDto(subject);
        assertEquals(dto.getSubject(), subject.getSubject());
        assertEquals((Long) dto.getId(), (Long) subject.getId());
    }

    @Test
    public void convertToEntity() throws Exception {
        SubjectDto dto = subjectConverter.convertToDto(subject);
        Subject secondSubject = subjectConverter.convertToEntity(dto);
        assertEquals(subject, secondSubject);
    }

}