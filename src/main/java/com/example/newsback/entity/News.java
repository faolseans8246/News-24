package com.example.newsback.entity;

import com.example.newsback.enums.ContentType;
import com.example.newsback.index.Ids;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.persistence.*;
import lombok.*;
import org.w3c.dom.Text;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "News")
public class News extends Ids {

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContentType contentType;

    @Lob
    private byte[] fileData; // faylni bazaga saqlash uchun

    private String fileName;  // asl fayl nomi
    private String fileType;  // mime type (audio/mp3, video/mp4)
}
