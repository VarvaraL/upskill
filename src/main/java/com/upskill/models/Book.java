package com.upskill.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "book", schema = "public")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String author;

    @Column(name = "book_is_booked")
    private boolean bookIsBooked;

    @ManyToOne
//    @JoinColumn(name = "application_id")
//    @JsonIgnore
    private Application usersApplication;

}
