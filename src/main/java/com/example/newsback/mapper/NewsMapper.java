package com.example.newsback.mapper;

import com.example.newsback.dto.NewsDto;
import com.example.newsback.dto.NewsTranslationDto;
import com.example.newsback.entity.News;
import com.example.newsback.entity.NewsTranslation;

import java.util.stream.Collectors;

public class NewsMapper {

    public static NewsDto toDto(News n) {
        if (n == null) return null;

        return new NewsDto(
                n.getId(),
                n.getContentType(),
                n.getFileName(),
                n.getFileType(),
                n.getFileSizeBytes(),
                n.getTranslations()
                        .stream()
                        .map(t -> new NewsTranslationDto(t.getLanguage(), t.getTitle(), t.getContent()))
                        .collect(Collectors.toList())
        );
    }
}
