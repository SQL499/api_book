package com.hampcode.service;

import com.hampcode.model.Author;
import com.hampcode.dto.AuthorRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final List<Author> authors = new ArrayList<>();
    private Long nextId = 1L;

    public AuthorService() {
        authors.add(new Author(nextId++, "Gabriel García Márquez"));
        authors.add(new Author(nextId++, "Isabel Allende"));
    }

    // Listar todos los autores
    public List<Author> findAll() {
        return authors;
    }

    // Buscar por ID
    public Author findById(Long id) {
        return authors.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Buscar autores por nombre (coincidencia parcial)
    public List<Author> findByName(String name) {
        return authors.stream()
                .filter(a -> a.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Crear nuevo autor con AuthorRequest
    public Author create(AuthorRequest request) {
        boolean exists = authors.stream()
                .anyMatch(a -> a.getName().equalsIgnoreCase(request.name()));
        if (exists) return null;

        Author author = new Author(nextId++, request.name());
        authors.add(author);
        return author;
    }

    // Actualizar autor con AuthorRequest
    public Author update(Long id, AuthorRequest request) {
        Author existing = findById(id);
        if (existing == null) return null;

        boolean duplicate = authors.stream()
                .anyMatch(a -> a.getName().equalsIgnoreCase(request.name()) && !a.getId().equals(id));
        if (duplicate) return null;

        existing.setName(request.name());
        return existing;
    }
}
