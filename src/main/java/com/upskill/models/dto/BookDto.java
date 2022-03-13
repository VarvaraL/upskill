package com.upskill.models.dto;

import com.upskill.models.Application;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class BookDto {
    private Long id;

    private String title;

    private String author;

    private boolean bookIsBooked;

    private ApplicationDto usersApplication;

    public BookDto(Long id, String title, String author, boolean bookIsBooked) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.bookIsBooked = bookIsBooked;
    }
}
