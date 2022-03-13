package com.upskill.models.dto;

import com.upskill.models.User;

import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class ApplicationDto {

    private Long id;

    private LocalDate dateOverBooking;

    private User user;

    private Set<BookDto> booksInApplication = new HashSet<>();

    public ApplicationDto(Long id, LocalDate dateOverBooking) {
        this.id = id;
        this.dateOverBooking = dateOverBooking;
    }
}
