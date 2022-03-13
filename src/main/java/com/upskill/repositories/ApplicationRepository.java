package com.upskill.repositories;

import com.upskill.models.Application;
import com.upskill.models.dto.ApplicationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query("SELECT NEW com.upskill.models.dto.ApplicationDto(" +
            "app.id, " +
            "app.dateOverBooking) " +
            "FROM Application app " +
            "LEFT JOIN User user ON (app.user.id = user.id) " +
            "LEFT JOIN Book book ON (app.id = book.usersApplication.id)")
    List<ApplicationDto> getAll();

    @Query("SELECT NEW com.upskill.models.dto.ApplicationDto(" +
            "app.id, " +
            "app.dateOverBooking) " +
            "FROM Application app " +
            "LEFT JOIN User user ON (app.user.id = user.id) " +
            "LEFT JOIN Book book ON (app.id = book.usersApplication.id) "+
            "WHERE app.id = :id")
    ApplicationDto getById(@Param("id") Long id);
}
