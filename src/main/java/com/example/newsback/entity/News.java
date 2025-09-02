// package com.example.newsback.entity.News.java
package com.example.newsback.entity;

import com.example.newsback.index.Ids; // Changed package name to base for better naming
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
@Table(name = "news")
public class News extends Ids {

    @Column(nullable = false)
    private String titleUz;

    @Column(nullable = false)
    private String titleEn;

    @Column(nullable = false)
    private String titleRu;

    @Column(columnDefinition = "TEXT")
    private String descriptionUz;

    @Column(columnDefinition = "TEXT")
    private String descriptionEn;

    @Column(columnDefinition = "TEXT")
    private String descriptionRu;

    @Column(columnDefinition = "TEXT")
    private String contentUz;   // HTML from CKEditor

    @Column(columnDefinition = "TEXT")
    private String contentEn;

    @Column(columnDefinition = "TEXT")
    private String contentRu;

    private String tags; // Comma-separated tags
}