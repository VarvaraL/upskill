package com.upskill.services;

import com.upskill.mappers.MapStructMapper;
import com.upskill.models.dto.ApplicationDto;
import com.upskill.models.dto.BookDto;
import com.upskill.repositories.ApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class ApplicationService {
    private final MapStructMapper mapStructMapper;
    private final ApplicationRepository applicationRepository;

    @Transactional
    public List<ApplicationDto> getAll (){
        return applicationRepository.getAll();
    }

    @Transactional
    public ApplicationDto getById(Long id) {
        return applicationRepository.getById(id);
    }

    @Transactional
    public void create(ApplicationDto applicationDto) {
        applicationRepository.save(mapStructMapper.applicationDtoToModelMapper(applicationDto));
    }

    @Transactional
    public void update(ApplicationDto applicationDto) {
        applicationRepository.save(mapStructMapper.applicationDtoToModelMapper(applicationDto));
    }

    @Transactional
    public void deleteById(Long id) {
        applicationRepository.deleteById(id);
    }
}
