package com.hampcode.service;

import com.hampcode.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service layer for managing {@link Category} entities.
 * <p>
 * This implementation keeps the categories in memory and is intended only for
 * demonstration purposes.
 * </p>
 */
@Service
public class CategoryService {

    private final List<Category> categories = new ArrayList<>();
    private Long nextId = 1L;

    public CategoryService() {
        categories.add(new Category(nextId++, "Ficción"));
        categories.add(new Category(nextId++, "Historia"));
        categories.add(new Category(nextId++, "Tecnología"));
    }

    /**
     * Retrieve all categories.
     */
    public List<Category> findAll() {
        return categories;
    }

    /**
     * Find a category by its identifier.
     */
    public Category findById(Long id) {
        return categories.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Find categories whose names contain the provided text.
     */
    public List<Category> findByName(String name) {
        return categories.stream()
                .filter(c -> c.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Create a new category with the given name.
     */
    public Category create(String name) {
        boolean exists = categories.stream()
                .anyMatch(c -> c.getName().equalsIgnoreCase(name));
        if (exists) return null;

        Category category = new Category(nextId++, name);
        categories.add(category);
        return category;
    }

    /**
     * Update the name of an existing category.
     */
    public Category update(Long id, String name) {
        Category existing = findById(id);
        if (existing == null) return null;

        boolean duplicate = categories.stream()
                .anyMatch(c -> c.getName().equalsIgnoreCase(name) && !c.getId().equals(id));
        if (duplicate) return null;

        existing.setName(name);
        return existing;
    }
}
