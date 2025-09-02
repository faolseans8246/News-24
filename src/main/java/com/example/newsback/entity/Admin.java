// package com.example.newsback.entity.Admin.java (Added for admin)
package com.example.newsback.entity;

import com.example.newsback.index.Ids; // Changed package name
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "admins")
public class Admin extends Ids {

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password; // Hashed

    @Column(nullable = false)
    private String role = "ADMIN";
}