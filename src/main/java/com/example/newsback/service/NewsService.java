// package com.example.newsback.service.NewsService.java (Added)
package com.example.newsback.service;

import com.example.newsback.dto.NewsDto;
import com.example.newsback.entity.News;
import com.example.newsback.mapper.NewsMapper;
import com.example.newsback.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;

    public List<NewsDto> getAll() {
        return newsRepository.findAll().stream()
                .map(NewsMapper::toDto)
                .collect(Collectors.toList());
    }

    public NewsDto getById(UUID id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("News not found with id: " + id));
        return NewsMapper.toDto(news);
    }

    public NewsDto create(NewsDto dto) {
        validateDto(dto);
        News news = NewsMapper.toEntity(dto);
        News saved = newsRepository.save(news);
        return NewsMapper.toDto(saved);
    }

    public NewsDto update(UUID id, NewsDto dto) {
        News existing = newsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("News not found with id: " + id));
        validateDto(dto);

        existing.setTitleUz(dto.titleUz());
        existing.setTitleEn(dto.titleEn());
        existing.setTitleRu(dto.titleRu());
        existing.setDescriptionUz(dto.descriptionUz());
        existing.setDescriptionEn(dto.descriptionEn());
        existing.setDescriptionRu(dto.descriptionRu());
        existing.setContentUz(dto.contentUz());
        existing.setContentEn(dto.contentEn());
        existing.setContentRu(dto.contentRu());
        existing.setTags(dto.tags());

        News updated = newsRepository.save(existing);
        return NewsMapper.toDto(updated);
    }

    public void delete(UUID id) {
        if (!newsRepository.existsById(id)) {
            throw new NoSuchElementException("News not found with id: " + id);
        }
        newsRepository.deleteById(id);
    }

    private void validateDto(NewsDto dto) {
        if (dto.titleUz() == null || dto.titleUz().isBlank()) {
            throw new IllegalArgumentException("Title in Uzbek is required");
        }
        if (dto.titleEn() == null || dto.titleEn().isBlank()) {
            throw new IllegalArgumentException("Title in English is required");
        }
        if (dto.titleRu() == null || dto.titleRu().isBlank()) {
            throw new IllegalArgumentException("Title in Russian is required");
        }
        // Additional validations can be added if needed
    }
}