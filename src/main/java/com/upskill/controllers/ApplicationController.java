package com.upskill.controllers;

import com.upskill.models.dto.ApplicationDto;
import com.upskill.models.dto.BookDto;
import com.upskill.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.upskill.services.ApplicationService;
import com.upskill.services.BookService;
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

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController("/api/users")
@Api(tags = "Application Rest Controller")
@Tag(name = "Application Rest Controller", description = "API for doing some CRUD with Application")
public class ApplicationController {
    private final ApplicationService applicationService;
    private final UserService userService;

    public ApplicationController(ApplicationService applicationService, UserService userService) {
        this.applicationService = applicationService;
        this.userService = userService;
    }

    @GetMapping("/{userid}/applications")
    @ApiOperation(
            value = "getAll",
            notes = "return List<ApplicationDto>",
            response = ApplicationDto.class,
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка Application"),
            @ApiResponse(code = 404, message = "Контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к операции"),
            @ApiResponse(code = 500, message = "Ошибка сервера")})
    public ResponseEntity<Optional<List<ApplicationDto>>> getAllApplications (
            @PathVariable("userid") Long userid){
        return applicationService.getAll(userid).isPresent()
                ? ResponseEntity.ok(applicationService.getAll(userid))
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{userid}/applications/{applicationid}")
    @ApiOperation(
            value = "getById",
            notes = "return Application",
            response = ApplicationDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение Application"),
            @ApiResponse(code = 404, message = "Контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к операции"),
            @ApiResponse(code = 500, message = "Ошибка сервера")})
    public ResponseEntity<Optional<ApplicationDto>> getById(
            @ApiParam(name = "applicationid", value = "id для получения Application", required = true)
            @PathVariable("userid") Long userid,
            @PathVariable("applicationid") Long id) {
        return applicationService.getById(id, userid).isPresent()
                ? ResponseEntity.ok(applicationService.getById(id, userid))
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping
    @ApiOperation(
            value = "create",
            notes = "Create Application")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное создание Application"),
            @ApiResponse(code = 404, message = "Контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к операции"),
            @ApiResponse(code = 500, message = "Ошибка сервера")})
    public ResponseEntity<ApplicationDto> create(
            @ApiParam(name = "ApplicationDto", value = "ApplicationDto for create Application", required = true)
            @PathVariable("userid") Long userid,
            @RequestBody ApplicationDto applicationDto) {
        applicationService.create(applicationDto, userid);
//        applicationDto.setUser(userService.getById(userid).get());

        return ResponseEntity.ok(applicationDto);
    }

    @PutMapping("/{userid}/applications")
    @ApiOperation(
            value = "update",
            notes = "Update Application")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное изменение Application"),
            @ApiResponse(code = 404, message = "Контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к операции"),
            @ApiResponse(code = 500, message = "Ошибка сервера")})
    public ResponseEntity<ApplicationDto> update(
            @ApiParam(name = "ApplicationDto", value = "ApplicationDto for update Application", required = true)
            @PathVariable("userid") Long userid,
            @RequestBody ApplicationDto applicationDto) {
        applicationService.update(applicationDto, userid);
        return ResponseEntity.ok(applicationDto);
    }

    @DeleteMapping("/{userid}/{applicationid}")
    @ApiOperation(
            value = "deleteById",
            notes = "Deleting a Application by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное удаление Application"),
            @ApiResponse(code = 404, message = "Контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к операции"),
            @ApiResponse(code = 500, message = "Ошибка сервера")})
    public ResponseEntity<ApplicationDto> deleteById(
            @ApiParam(name = "applicationid", value = "id удаляемого Application", required = true)
            @PathVariable("userid") Long userid,
            @PathVariable("applicationid") Long applicationId){
        applicationService.deleteById(userid, applicationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}