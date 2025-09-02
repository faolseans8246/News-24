// package com.example.newsback.controller.NewsController.java (Added)
package com.example.newsback.controller;

import com.example.newsback.dto.NewsDto;
import com.example.newsback.payloads.ApiResponse;
import com.example.newsback.service.NewsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        List<NewsDto> newsList = newsService.getAll();
        return ResponseEntity.ok(new ApiResponse("News retrieved successfully", true, newsList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable UUID id) {
        NewsDto newsDto = newsService.getById(id);
        return ResponseEntity.ok(new ApiResponse("News retrieved successfully", true, newsDto));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody NewsDto dto) {
        NewsDto created = newsService.create(dto);
        return ResponseEntity.status(201).body(new ApiResponse("News created successfully", true, created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable UUID id, @Valid @RequestBody NewsDto dto) {
        NewsDto updated = newsService.update(id, dto);
        return ResponseEntity.ok(new ApiResponse("News updated successfully", true, updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable UUID id) {
        newsService.delete(id);
        return ResponseEntity.ok(new ApiResponse("News deleted successfully", true));
    }
}