package com.upskill.repositories;


import com.upskill.models.Book;
import com.upskill.models.dto.BookDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT NEW com.upskill.models.dto.BookDto(" +
            "book.id, " +
            "book.title, " +
            "book.author, " +
            "book.bookIsBooked " +
            ") " +
            "FROM Book book " +
            "LEFT JOIN Application app ON (book.usersApplication.id = app.id)")
    List<BookDto> getAll();

    @Query("SELECT NEW com.upskill.models.dto.BookDto(" +
            "book.id, " +
            "book.title, " +
            "book.author, " +
            "book.bookIsBooked) " +
            "FROM Book book " +
            "LEFT JOIN Application app ON (book.usersApplication.id = app.id) " +
            "WHERE book.id = :id")
    BookDto getById(@Param("id") Long id);
}
