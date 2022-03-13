package com.upskill.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
public class UserPostDto {
    @JsonProperty("id")
    private Long id;

    @Email
    @NotNull
    @JsonProperty("username")
    private String username;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @NotNull
    @JsonProperty("password")
    private String password;

    @JsonProperty("usersApplications")
    private Set<ApplicationDto> usersApplications;

    public UserPostDto(Long id, String username, String firstName, String lastName, String password) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }
}
