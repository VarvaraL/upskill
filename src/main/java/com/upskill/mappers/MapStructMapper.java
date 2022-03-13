package com.upskill.mappers;

import com.upskill.models.Application;
import com.upskill.models.Book;
import com.upskill.models.User;
import com.upskill.models.dto.*;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface MapStructMapper {

    Application applicationDtoToModelMapper(ApplicationDto applicationDto);

    BookDto bookToBookDto(Book book);

    Book bookDtoToModelMapper(BookDto bookDto);

    User userPostDtoToUser(UserPostDto userPostDto);

}
