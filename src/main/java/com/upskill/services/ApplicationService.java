package com.upskill.services;

import com.upskill.mappers.MapStructMapper;
import com.upskill.models.dto.ApplicationDto;
import com.upskill.models.dto.BookDto;
import com.upskill.repositories.ApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class ApplicationService {
    private final MapStructMapper mapStructMapper;
    private final ApplicationRepository applicationRepository;

    @Transactional
    public Optional<List<ApplicationDto>> getAll (Long userId){
        return Optional.of(applicationRepository.getAll(userId));
    }

    @Transactional
    public Optional<ApplicationDto> getById(Long userId, Long applicationId) {
        return Optional.of(applicationRepository.getById(userId, applicationId));
    }

    @Transactional
    public void create(ApplicationDto applicationDto, Long userId) {
        Set<BookDto> booksInApplication = applicationDto.getBooksInApplication();
        for (BookDto bookDto: booksInApplication) {
            bookDto.setBookIsBooked(true);
        }
        applicationRepository.save(mapStructMapper.applicationDtoToModelMapper(applicationDto, userId));
    }

    @Transactional
    public void update(ApplicationDto applicationDto, Long userId) {
        applicationRepository.save(mapStructMapper.applicationDtoToModelMapper(applicationDto, userId));
    }

    @Transactional
    public void deleteById(Long userid, Long applicationId) {
        applicationRepository.deleteById(userid, applicationId);
    }
}
