// package com.example.newsback.service.AdminService.java (Added)
package com.example.newsback.service;

import com.example.newsback.dto.AdminDto;
import com.example.newsback.entity.Admin;
import com.example.newsback.payloads.ApiResponse;
import com.example.newsback.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public ApiResponse register(AdminDto dto) {
        if (dto.username() == null || dto.username().isBlank()) {
            return new ApiResponse("Username is required", false);
        }
        if (dto.password() == null || dto.password().length() < 6) {
            return new ApiResponse("Password must be at least 6 characters", false);
        }
        if (adminRepository.existsByUsername(dto.username())) {
            return new ApiResponse("Admin with this username already exists", false);
        }

        Admin admin = Admin.builder()
                .username(dto.username())
                .password(passwordEncoder.encode(dto.password()))
                .role("ADMIN")
                .build();

        adminRepository.save(admin);
        return new ApiResponse("Admin registered successfully", true);
    }
}