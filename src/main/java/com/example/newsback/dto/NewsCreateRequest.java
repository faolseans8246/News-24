package com.example.newsback.dto;

import com.example.newsback.enums.ContentType;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record NewsCreateRequest(
        @NotNull ContentType contentType,
        List<NewsTranslationDto> translations
) {}
