package com.upskill.controllers;

import com.upskill.models.dto.BookDto;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RequestMapping("")
@RestController
@Api(tags = "Book Rest Controller")
@Tag(name = "Book Rest Controller", description = "API for doing some CRUD with Book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/api/books")
    @ApiOperation(
            value = "getAll",
            notes = "return List<BookDto>",
            response = BookDto.class,
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение списка Book"),
            @ApiResponse(code = 404, message = "Контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к операции"),
            @ApiResponse(code = 500, message = "Ошибка сервера")})
    public ResponseEntity<List<BookDto>> getAllBooks (){
        return bookService.getAll().isPresent()
                ? ResponseEntity.of(bookService.getAll())
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "getById",
            notes = "return Book",
            response = BookDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное получение Book"),
            @ApiResponse(code = 404, message = "Контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к операции"),
            @ApiResponse(code = 500, message = "Ошибка сервера")})
    @GetMapping("/api/books/{id}")
    public ResponseEntity<BookDto> getById(
            @ApiParam(name = "id", value = "id для получения Book", required = true)
            @PathVariable("id") Long id) {
        return bookService.getById(id).isPresent()
                ? ResponseEntity.ok(bookService.getById(id).get())
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(
            value = "create",
            notes = "Create Book")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное создание Book"),
            @ApiResponse(code = 404, message = "Контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к операции"),
            @ApiResponse(code = 500, message = "Ошибка сервера")})
    @PostMapping("/api/books")
    public ResponseEntity<BookDto> create(
            @ApiParam(name = "BookDto", value = "BookDto for create Task", required = true)
            @RequestBody BookDto bookDto) {
        bookService.create(bookDto);
        return ResponseEntity.ok(bookDto);
    }

    @ApiOperation(
            value = "update",
            notes = "Update Book")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное изменение Book"),
            @ApiResponse(code = 404, message = "Контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к операции"),
            @ApiResponse(code = 500, message = "Ошибка сервера")})
    @PutMapping("/api/books")
    public ResponseEntity<BookDto> update(
            @ApiParam(name = "BookDto", value = "BookDto for update Book", required = true)
            @RequestBody BookDto bookDto) {
        bookService.update(bookDto);
        return ResponseEntity.ok(bookDto);
    }


    @DeleteMapping("/api/books/{id}")
    @ApiOperation(
            value = "deleteById",
            notes = "Deleting a Book by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное удаление Book"),
            @ApiResponse(code = 404, message = "Контроллер не найден"),
            @ApiResponse(code = 403, message = "Операция запрещена"),
            @ApiResponse(code = 401, message = "Нет доступа к операции"),
            @ApiResponse(code = 500, message = "Ошибка сервера")})
    public ResponseEntity<BookDto> deleteById(
            @ApiParam(name = "id", value = "id удаляемого Book", required = true)
            @PathVariable("id") Long id){
        bookService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
