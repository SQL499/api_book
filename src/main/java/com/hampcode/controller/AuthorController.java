package com.hampcode.controller;

import com.hampcode.model.Author;
import com.hampcode.service.AuthorService;
import com.hampcode.dto.AuthorRequest;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // GET /api/v1/authors
    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.findAll();
    }

    // GET /api/v1/authors/{id}
    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        return authorService.findById(id);
    }

    // GET /api/v1/authors/search?name=...
    @GetMapping("/search")
    public List<Author> searchAuthorsByName(@RequestParam String name) {
        return authorService.findByName(name);
    }

    // POST /api/v1/authors
    @PostMapping
    public Author createAuthor(@RequestBody AuthorRequest request) {
        Author created = authorService.create(request);
        if (created == null) {
            throw new RuntimeException("Ya existe un autor con ese nombre.");
        }
        return created;
    }

    // PUT /api/v1/authors/{id}
    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable Long id, @RequestBody AuthorRequest request) {
        Author updated = authorService.update(id, request);
        if (updated == null) {
            throw new RuntimeException("No se pudo actualizar: autor no encontrado o nombre duplicado.");
        }
        return updated;
    }
}
