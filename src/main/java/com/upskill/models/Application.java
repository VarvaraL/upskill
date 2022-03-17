package com.upskill.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "Application", schema = "public")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_over_booking")
    private LocalDate dateOverBooking;

//    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne
    @JsonIgnore
    private User user;


    @OneToMany(mappedBy = "usersApplication")
    private Set<Book> booksInApplication;

}
