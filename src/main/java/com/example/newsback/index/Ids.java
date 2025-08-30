package com.example.newsback.index;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
@EntityListeners(ArithmeticException.class)
public abstract class Ids implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UUID", unique = true, nullable = false, updatable = false)
    private UUID id;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createTime;

    @Column(name = "update_at")
    @UpdateTimestamp
    private Timestamp updateTime;
}
