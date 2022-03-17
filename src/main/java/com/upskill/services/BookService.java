package com.upskill.services;

import com.upskill.mappers.MapStructMapper;
import com.upskill.models.dto.BookDto;
import com.upskill.repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BookService {
    private final MapStructMapper mapStructMapper;
    private final BookRepository bookRepository;

    @Transactional
    public Optional<List<BookDto>> getAll() {
        return Optional.of(bookRepository.getAll());
    }

    @Transactional
    public Optional<BookDto> getById(Long id) {
        return Optional.of(bookRepository.getById(id));
    }

    @Transactional
    public void create(BookDto bookDto) {
        bookRepository.save(mapStructMapper.bookDtoToModelMapper(bookDto));
    }

    @Transactional
    public void update(BookDto bookDto) {
        bookRepository.save(mapStructMapper.bookDtoToModelMapper(bookDto));
    }

    @Transactional
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
