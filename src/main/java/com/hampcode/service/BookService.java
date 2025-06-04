package com.hampcode.service;

import com.hampcode.model.Author;
import com.hampcode.model.Book;
import com.hampcode.model.Category;
import com.hampcode.dto.BookRequest;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final List<Book> books = new ArrayList<>();
    private final List<Author> authors = new ArrayList<>();
    private final List<Category> categories = new ArrayList<>();
    private Long nextId = 1L;

    public BookService() {

        authors.add(new Author(1L, "Gabriel García Márquez"));
        authors.add(new Author(2L, "Isabel Allende"));
        authors.add(new Author(3L, "Mario Vargas Llosa"));


        categories.add(new Category(1L, "Ficción"));
        categories.add(new Category(2L, "Historia"));
        categories.add(new Category(3L, "Tecnología"));
    }

    public Book registerBook(BookRequest request) {
        Author author = getAuthorById(request.authorId());
        Category category = getCategoryById(request.categoryId());

        if (author == null || category == null) {
            return null;
        }

        Book book = new Book(nextId++, request.title(), request.year(),
                request.description(), request.imageUrl(), author, category);
        books.add(book);
        return book;
    }

    public Book updateBook(Long bookId, BookRequest request) {
        Book existing = getBookById(bookId);
        if (existing == null) {
            return null;
        }

        Author author = getAuthorById(request.authorId());
        Category category = getCategoryById(request.categoryId());

        if (author == null || category == null) {
            return null;
        }

        existing.setTitle(request.title());
        existing.setYear(request.year());
        existing.setDescription(request.description());
        existing.setImageUrl(request.imageUrl());
        existing.setAuthor(author);
        existing.setCategory(category);

        return existing;
    }


    public List<Book> findAll() {
        return books;
    }

    public List<Book> findByAuthor(Long authorId) {
       return books.stream()
               .filter(b -> b.getAuthor() != null && b.getAuthor().getId().equals(authorId))
               .collect(Collectors.toList());
    }

    private Author getAuthorById(Long authorId) {
        return authors.stream()
                .filter(a -> a.getId().equals(authorId))
                .findFirst()
                .orElse(null);
    }

    private Category getCategoryById(Long categoryId) {
        return categories.stream()
                .filter(c -> c.getId().equals(categoryId))
                .findFirst()
                .orElse(null);
    }

    private Book getBookById(Long bookId) {
        return books.stream()
                .filter(b -> b.getId().equals(bookId))
                .findFirst()
                .orElse(null);
    }

}
