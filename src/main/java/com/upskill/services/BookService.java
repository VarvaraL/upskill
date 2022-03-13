package com.upskill.services;

import com.upskill.mappers.MapStructMapper;
import com.upskill.models.dto.BookDto;
import com.upskill.repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class BookService {
    private final MapStructMapper mapStructMapper;
    private final BookRepository bookRepository;

    @Transactional
    public List<BookDto> getAll() {
        System.out.println("сервис книги " + bookRepository.getAll());
        return bookRepository.getAll();
    }

    @Transactional
    public BookDto getById(Long id) {
        System.out.println("сервис книги " + getById(id));
        return bookRepository.getById(id);
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
