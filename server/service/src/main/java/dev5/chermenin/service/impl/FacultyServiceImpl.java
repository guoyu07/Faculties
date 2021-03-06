package dev5.chermenin.service.impl;

import dev5.chermenin.dao.repository.FacultyRepository;
import dev5.chermenin.dao.repository.UniversityRepository;
import dev5.chermenin.model.entity.impl.Faculty;
import dev5.chermenin.service.api.FacultyService;
import dev5.chermenin.service.dto.impl.FacultyDto;
import dev5.chermenin.service.util.converters.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {

    private final Converter<FacultyDto, Faculty> facultyConverter;
    private final FacultyRepository facultyRepository;
    private final UniversityRepository universityRepository;

    @Transactional(readOnly = true)
    @Override
    public FacultyDto findById(Long id) {
        Faculty faculty = facultyRepository.findOne(id);
        faculty.getGroups().forEach(group -> group.setUsers(null));
        return facultyConverter.convertToDto(faculty);
    }

    @Transactional(readOnly = true)
    @Override
    public List<FacultyDto> findAll(Pageable pageable) {
        List<Faculty> faculties = facultyRepository.findAll(pageable).getContent();
        faculties.forEach(group -> group.setGroups(null));
        return facultyConverter.convertToDto(faculties);

    }

    @Override
    public void remove(Long id) {
        facultyRepository.delete(id);
    }

    @Override
    public void removeAll() {
        facultyRepository.deleteAll();
    }

    @Override
    public FacultyDto save(FacultyDto facultyDto) {
        Faculty faculty = new Faculty();
        faculty.setInformation(facultyDto.getInformation());
        faculty.setName(facultyDto.getName());
        faculty.setUniversity(universityRepository.findOne(facultyDto.getUniversityId()));
        return facultyConverter.convertToDto(facultyRepository.save(faculty));
    }

    @Transactional
    @Override
    public void update(FacultyDto dto) {
        Faculty faculty = facultyRepository.findOne(dto.getId());

        faculty.setName(dto.getName());
        faculty.setInformation(dto.getInformation());
    }
}
