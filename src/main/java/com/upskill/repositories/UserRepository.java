package com.upskill.repositories;

import com.upskill.models.User;
import com.upskill.models.dto.UserGetDto;
import com.upskill.models.dto.UserPostDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT NEW com.upskill.models.dto.UserGetDto(" +
            "user.id, " +
            "user.username, " +
            "user.firstName, " +
            "user.lastName) " +
            "FROM User user " +
            "LEFT JOIN Application app ON (user.id = app.user.id)")
    List<UserGetDto> getAll();

    @Query("SELECT NEW com.upskill.models.dto.UserGetDto(" +
            "user.id, " +
            "user.username, " +
            "user.firstName, " +
            "user.lastName) " +
            "FROM User user " +
            "LEFT JOIN Application app ON (user.id = app.user.id)" +
            "WHERE user.id = :id")
    UserGetDto getById(@Param("id") Long id);

    @Query("SELECT NEW com.upskill.models.dto.UserPostDto(" +
            "user.id, " +
            "user.username, " +
            "user.firstName, " +
            "user.lastName, " +
            "user.password)" +
            "FROM User user " +
            "LEFT JOIN Application app ON (user.id = app.user.id)" +
            "WHERE user.username = :username")
    UserPostDto getByUsername(@Param("username") String username);
}