package com.example.newsback.service;

import com.example.newsback.dto.NewsCreateRequest;
import com.example.newsback.dto.NewsDto;
import com.example.newsback.dto.NewsTranslationDto;
import com.example.newsback.entity.News;
import com.example.newsback.entity.NewsTranslation;
import com.example.newsback.enums.ContentType;
import com.example.newsback.mapper.NewsMapper;
import com.example.newsback.repository.NewsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;

    @Transactional
    public NewsDto create(NewsCreateRequest req, MultipartFile file) throws IOException {
        validateCreate(req, file);

        News news = News.builder()
                .contentType(req.contentType())
                .build();

        if (file != null && !file.isEmpty()) {
            news.setFileData(file.getBytes());
            news.setFileName(file.getOriginalFilename());
            news.setFileType(file.getContentType() != null ? file.getContentType() : MediaType.APPLICATION_OCTET_STREAM_VALUE);
            news.setFileSizeBytes(file.getSize());
        }

        List<NewsTranslation> translations = req.translations().stream()
                .map(t -> NewsTranslation.builder()
                        .news(news)
                        .language(t.language())
                        .title(t.title())
                        .content(t.content())
                        .build())
                .toList();

        news.setTranslations(translations);

        News saved = newsRepository.save(news);
        return NewsMapper.toDto(saved);
    }

    public List<NewsDto> getAll() {
        return newsRepository.findAll().stream().map(NewsMapper::toDto).toList();
    }

    public News getEntity(UUID id) {
        return newsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Yangilik topilmadi: " + id));
    }

    public NewsDto get(UUID id) {
        return NewsMapper.toDto(getEntity(id));
    }

    public void delete(UUID id) {
        newsRepository.deleteById(id);
    }

    /* ======== Helperlar ======== */

    private void validateCreate(NewsCreateRequest req, MultipartFile file) {
        if (req.contentType() == ContentType.TEXT) {
            return;
        }
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("AUDIO/VIDEO uchun fayl majburiy.");
        }
    }
}
