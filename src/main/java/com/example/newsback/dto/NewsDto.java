package com.example.newsback.dto;

import com.example.newsback.enums.ContentType;

import java.util.List;
import java.util.UUID;

public record NewsDto(
        UUID id,
        ContentType contentType,
        String fileName,
        String fileType,
        Long fileSizeBytes,
        List<NewsTranslationDto> translations
) {}
