package com.upskill.models;

import lombok.*;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "Application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_over_booking")
    private LocalDate dateOverBooking;

    @ManyToOne
    private User user;

    @OneToMany(fetch=FetchType.EAGER)
    private Set<Book> booksInApplication;

}
