// package com.example.newsback.security.UserDetailsServiceImpl.java (Added)
package com.example.newsback.security;

import com.example.newsback.entity.Admin;
import com.example.newsback.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> optionalAdmin = adminRepository.findByUsername(username);
        Admin admin = optionalAdmin.orElseThrow(() -> new UsernameNotFoundException("Admin not found: " + username));

        return User.withUsername(admin.getUsername())
                .password(admin.getPassword())
                .roles(admin.getRole())
                .build();
    }
}