package com.hampcode.controller;

import com.hampcode.model.Book;
import com.hampcode.dto.BookRequest;
import com.hampcode.service.BookService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;



@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    //inyectando la dependencia del BookService en el BookController
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookRequest bookRequest) {
        Book book = bookService.registerBook(bookRequest);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

}
