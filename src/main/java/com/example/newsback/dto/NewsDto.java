package com.example.newsback.dto;

import com.example.newsback.enums.ContentType;

import java.util.UUID;

public record NewsDto(
        UUID id,
        String title,
        String content,
        String fileName,
        ContentType contentType
) {
}
