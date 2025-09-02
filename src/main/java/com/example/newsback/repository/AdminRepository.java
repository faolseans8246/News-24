// package com.example.newsback.repository.AdminRepository.java (Added)
package com.example.newsback.repository;

import com.example.newsback.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID> {

    Optional<Admin> findByUsername(String username);

    boolean existsByUsername(String username);
}