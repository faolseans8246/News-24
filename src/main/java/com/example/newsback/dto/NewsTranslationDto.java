package com.example.newsback.dto;

import com.example.newsback.enums.Language;

public record NewsTranslationDto(
        Language language,
        String title,
        String content
) {}
