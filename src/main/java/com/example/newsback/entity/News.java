package com.example.newsback.entity;

import com.example.newsback.enums.ContentType;
import com.example.newsback.index.Ids;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

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
    @Enumerated(EnumType.STRING)
    private ContentType contentType;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "file_data")
    private byte[] fileData; // faylning o'zi (BLOB)

    private String fileName;
    private String fileType;
    private Long fileSizeBytes;

    // Har bir yangilik 3 tilda tarjimaga ega bo'ladi
    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<NewsTranslation> translations;
}
