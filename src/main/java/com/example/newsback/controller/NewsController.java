package com.example.newsback.controller;

import com.example.newsback.dto.NewsCreateRequest;
import com.example.newsback.dto.NewsDto;
import com.example.newsback.payloads.ApiResponse;
import com.example.newsback.service.NewsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
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

    @Operation(summary = "Yangilik yaratish (3 til uchun)")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> createNews(
            @RequestParam("request") String requestJson,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) throws IOException {
        try {
            // JSON → DTO
            ObjectMapper mapper = new ObjectMapper();
            NewsCreateRequest request = mapper.readValue(requestJson, NewsCreateRequest.class);

            // Service orqali yangilik yaratish
            NewsDto dto = newsService.create(request, file);

            return ResponseEntity.ok(new ApiResponse("Yangilik qo'shildi", true, dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse("Xato: " + e.getMessage(), false));
        }
    }


    @GetMapping
    public ResponseEntity<List<NewsDto>> getAllNews() {
        return ResponseEntity.ok(newsService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsDto> getNews(@PathVariable UUID id) {
        return ResponseEntity.ok(newsService.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteNews(@PathVariable UUID id) {
        newsService.delete(id);
        return ResponseEntity.ok(new ApiResponse("Yangilik o'chirildi", true));
    }

    @GetMapping("/{id}/file")
    public ResponseEntity<Resource> downloadFile(@PathVariable UUID id) {
        var news = newsService.getEntity(id);
        if (news.getFileData() == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        ByteArrayResource resource = new ByteArrayResource(news.getFileData());
        String filename = news.getFileName() != null ? news.getFileName() : ("file-" + id);
        String contentType = news.getFileType() != null ? news.getFileType() : MediaType.APPLICATION_OCTET_STREAM_VALUE;

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .contentLength(news.getFileSizeBytes() != null ? news.getFileSizeBytes() : news.getFileData().length)
                .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment().filename(filename).build().toString())
                .body(resource);
    }
}
