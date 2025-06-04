package com.hampcode.controller;

import com.hampcode.model.Book;
import com.hampcode.dto.BookRequest;
import com.hampcode.service.BookService;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;



@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    // GET /api/v1/books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    // GET /api/v1/books/author/{authorId}
    @GetMapping("/author/{authorId}")
    public List<Book> getBooksByAuthor(@PathVariable Long authorId) {
        return bookService.findByAuthor(authorId);
    }

    // POST /api/v1/books
    @PostMapping
    public Book createBook(@RequestBody BookRequest request) {
        Book created = bookService.registerBook(request);
        if (created == null) {
            throw new RuntimeException("Autor o categoría inválido.");
        }
        return created;
    }

    // PUT /api/v1/books/{id}
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody BookRequest request) {
        Book updated = bookService.updateBook(id, request);
        if (updated == null) {
            throw new RuntimeException("No se pudo actualizar el libro.");
        }
        return updated;
    }
}
