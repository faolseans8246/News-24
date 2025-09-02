// package com.example.newsback.mapper.NewsMapper.java
package com.example.newsback.mapper;

import com.example.newsback.dto.NewsDto;
import com.example.newsback.entity.News;

public class NewsMapper {

    public static NewsDto toDto(News news) {
        if (news == null) return null;

        return new NewsDto(
                news.getTitleUz(),
                news.getTitleEn(),
                news.getTitleRu(),
                news.getDescriptionUz(),
                news.getDescriptionEn(),
                news.getDescriptionRu(),
                news.getContentUz(),
                news.getContentEn(),
                news.getContentRu(),
                news.getTags()
        );
    }

    public static News toEntity(NewsDto dto) {
        if (dto == null) return null;

        return News.builder()
                .titleUz(dto.titleUz())
                .titleEn(dto.titleEn())
                .titleRu(dto.titleRu())
                .descriptionUz(dto.descriptionUz())
                .descriptionEn(dto.descriptionEn())
                .descriptionRu(dto.descriptionRu())
                .contentUz(dto.contentUz())
                .contentEn(dto.contentEn())
                .contentRu(dto.contentRu())
                .tags(dto.tags())
                .build();
    }
}