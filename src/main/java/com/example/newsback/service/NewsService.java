package com.example.newsback.service;

import com.example.newsback.dto.NewsCreateDto;
import com.example.newsback.entity.News;
import com.example.newsback.payloads.ApiResponse;
import com.example.newsback.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;

    public ApiResponse createNews(NewsCreateDto dto) throws IOException {
        News news = News.builder()
                .title(dto.title())
                .content(dto.content())
                .contentType(dto.contentType())
                .build();

        MultipartFile file = dto.file();
        if (file != null && !file.isEmpty()) {
            news.setFileData(file.getBytes());
            news.setFileName(file.getOriginalFilename());
            news.setFileType(file.getContentType());
        }

        newsRepository.save(news);
        return new ApiResponse("Yangilik qo'shildi", true, news);
    }

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    public News getNews(UUID id) {
        return newsRepository.findById(id).orElseThrow(() -> new RuntimeException("Yangilik topilmadi"));
    }

    public ApiResponse deleteNews(UUID id) {
        newsRepository.deleteById(id);
        return new ApiResponse("Yangilik o'chirildi", true);
    }

    public ApiResponse updateNews(UUID id, NewsCreateDto dto) throws IOException {
        News news = newsRepository.findById(id).orElseThrow(() -> new RuntimeException("Yangilik topilmadi"));

        news.setTitle(dto.title());
        news.setContent(dto.content());
        news.setContentType(dto.contentType());

        MultipartFile file = dto.file();
        if (file != null && !file.isEmpty()) {
            news.setFileData(file.getBytes());
            news.setFileName(file.getOriginalFilename());
            news.setFileType(file.getContentType());
        }

        newsRepository.save(news);
        return new ApiResponse("Yangilik yangilandi", true, news);
    }
}