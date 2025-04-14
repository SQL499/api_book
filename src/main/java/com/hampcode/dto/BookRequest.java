package com.hampcode.dto;

public record BookRequest(
        String title,
        int year,
        String description,
        String imageUrl,
        Long authorId,
        Long categoryId
) {}

