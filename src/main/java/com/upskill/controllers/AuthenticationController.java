package com.upskill.controllers;

import com.upskill.models.dto.UserPostDto;
import com.upskill.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("api")
public class AuthenticationController {
    private UserService service;

    public AuthenticationController(UserService service) {
        this.service = service;
    }

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    UserPostDto getAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        UserPostDto user = (principal instanceof User) ? (UserPostDto) principal : null;
        return Objects.nonNull(user) ? this.service.getByUsername(user.getUsername()) : null;
    }
}
