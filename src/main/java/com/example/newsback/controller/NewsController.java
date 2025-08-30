package com.example.newsback.controller;

import com.example.newsback.dto.NewsCreateDto;
import com.example.newsback.payloads.ApiResponse;
import com.example.newsback.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @PostMapping
    public ResponseEntity<ApiResponse> createNews(@RequestParam String title,
                                                  @RequestParam(required = false) String content,
                                                  @RequestParam String contentType,
                                                  @RequestParam(required = false) MultipartFile file) throws IOException {
        return ResponseEntity.ok(newsService.createNews(new NewsCreateDto(
                title, content, Enum.valueOf(com.example.newsback.enums.ContentType.class, contentType), file
        )));
    }

    @GetMapping
    public ResponseEntity<List<?>> getAllNews() {
        return ResponseEntity.ok(newsService.getAllNews());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNews(@PathVariable UUID id) {
        return ResponseEntity.ok(newsService.getNews(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteNews(@PathVariable UUID id) {
        return ResponseEntity.ok(newsService.deleteNews(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateNews(@PathVariable UUID id,
                                                  @RequestParam String title,
                                                  @RequestParam(required = false) String content,
                                                  @RequestParam String contentType,
                                                  @RequestParam(required = false) MultipartFile file) throws IOException {
        return ResponseEntity.ok(newsService.updateNews(id, new NewsCreateDto(
                title, content, Enum.valueOf(com.example.newsback.enums.ContentType.class, contentType), file
        )));
    }
}