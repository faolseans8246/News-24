// package com.example.newsback.controller.AdminController.java (Added)
package com.example.newsback.controller;

import com.example.newsback.dto.AdminDto;
import com.example.newsback.payloads.ApiResponse;
import com.example.newsback.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody AdminDto dto) {
        ApiResponse response = adminService.register(dto);
        if (response.isSuccess()) {
            return ResponseEntity.status(201).body(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    // No login endpoint needed as Basic auth handles authentication for protected routes
}