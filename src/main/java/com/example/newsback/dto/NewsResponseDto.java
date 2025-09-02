// package com.example.newsback.dto.NewsResponseDto.java (New: For response, including id, dates, etc.)

package com.example.newsback.dto;

import java.sql.Timestamp;
import java.util.UUID;

public record NewsResponseDto(
        UUID id,
        String titleUz,
        String titleEn,
        String titleRu,
        String descriptionUz,
        String descriptionEn,
        String descriptionRu,
        String contentUz,
        String contentEn,
        String contentRu,
        String tags,
        Timestamp createdAt,
        Timestamp updatedAt,
        Timestamp publishedAt,
        boolean isPublished,
        long viewCount
) {}