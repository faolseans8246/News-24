package com.example.newsback.dto;

import com.example.newsback.enums.ContentType;
import org.springframework.web.multipart.MultipartFile;

public record NewsCreateDto(
        String title,
        String content,
        ContentType contentType,
        MultipartFile file // text bo'lsa null
) {
}
