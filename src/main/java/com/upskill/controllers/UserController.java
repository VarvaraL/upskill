package com.upskill.controllers;

import com.upskill.models.dto.UserGetDto;
import com.upskill.models.dto.UserPostDto;
import com.upskill.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@RequestMapping("")
@RestController
@Api(tags = "User Rest Controller")
@Tag(name = "User Rest Controller", description = "API for doing some CRUD with User")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(
            value = "getAll",
            notes = "return List<UserGetDto>",
            response = UserGetDto.class,
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка User"),
            @ApiResponse(code = 404, message = "Контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к операции"),
            @ApiResponse(code = 500, message = "Ошибка сервера")})
    @GetMapping("/api/users")
    public ResponseEntity<Optional<List<UserGetDto>>> getAllUsers (){
        return userService.getAll().isPresent()
                ? ResponseEntity.ok(userService.getAll())
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "getById",
            notes = "return User",
            response = UserGetDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение User"),
            @ApiResponse(code = 404, message = "Контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к операции"),
            @ApiResponse(code = 500, message = "Ошибка сервера")})
    @GetMapping("/api/users/{userid}")
    public ResponseEntity<UserGetDto> getById(
            @ApiParam(name = "userid", value = "id для получения User", required = true)
            @PathVariable("userid") Long id) {
        return userService.getById(id).isPresent()
                ? ResponseEntity.ok(userService.getById(id).get())
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "create",
            notes = "Create User")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное создание User"),
            @ApiResponse(code = 404, message = "Контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к операции"),
            @ApiResponse(code = 500, message = "Ошибка сервера")})
    @PostMapping("/api/users")
    public ResponseEntity<UserPostDto> create(
            @ApiParam(name = "UserPostDto", value = "UserPostDto for create User", required = true)
            @RequestBody UserPostDto userDto) {
        userService.create(userDto);
        return ResponseEntity.ok(userDto);
    }

    @ApiOperation(
            value = "update",
            notes = "Update User")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное изменение User"),
            @ApiResponse(code = 404, message = "Контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к операции"),
            @ApiResponse(code = 500, message = "Ошибка сервера")})
    @PutMapping("/api/users/{userid}")
    public ResponseEntity<UserPostDto> update(
            @ApiParam(name = "UserPostDto", value = "UserPostDto for update User", required = true)
            @RequestBody UserPostDto userDto) {
        userService.update(userDto);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/api/users/{userid}")
    @ApiOperation(
            value = "deleteById",
            notes = "Deleting a User by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное удаление User"),
            @ApiResponse(code = 404, message = "Контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к операции"),
            @ApiResponse(code = 500, message = "Ошибка сервера")})
    public ResponseEntity<UserPostDto> deleteById(
            @ApiParam(name = "userid", value = "userId удаляемого User", required = true)
            @PathVariable("userid") Long id){
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
