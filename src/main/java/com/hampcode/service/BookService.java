package com.hampcode.service;

import com.hampcode.model.Author;
import com.hampcode.model.Book;
import com.hampcode.model.Category;
import com.hampcode.dto.BookRequest;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        Author author = authors.stream()
                .filter(a->a.getId()
                        .equals(request.authorId()))
                .findFirst().orElse(null);
            Category category = categories.stream()
                    .filter(c->c.getId()
                            .equals(request.categoryId()))
                    .findFirst().orElse(null);
            boolean exists = books.stream()
                    .anyMatch(b->b.getTitle()
                    .equals(request.title()) &&
                            b.getAuthor().getId().equals(author.getId()));
            if (exists) {
                return null;
            }
            Book newBook = new Book(
                    nextId++,
                    request.title(),
                    request.year(),
                    request.description(),
                    request.imageUrl(),
                    author,
                    category
            );
            books.add(newBook);
            return newBook;
    }

    public Book updateBook(Long bookId, BookRequest request) {
        return null;
    }


    public List<Book> findAll() {
        return books;
    }

    public List<Book> findByAuthor(Long authorId) {
       return null;
    }

}
