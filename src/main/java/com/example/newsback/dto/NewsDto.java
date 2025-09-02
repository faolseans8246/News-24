// package com.example.newsback.dto.NewsDto.java
package com.example.newsback.dto;

public record NewsDto(
        String titleUz,
        String titleEn,
        String titleRu,
        String descriptionUz,
        String descriptionEn,
        String descriptionRu,
        String contentUz,
        String contentEn,
        String contentRu,
        String tags
) {}