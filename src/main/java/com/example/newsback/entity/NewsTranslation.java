package com.example.newsback.entity;

import com.example.newsback.enums.Language;
import com.example.newsback.index.Ids;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "news_translation")
public class NewsTranslation extends Ids {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id", nullable = false)
    private News news;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Language language;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;
}
