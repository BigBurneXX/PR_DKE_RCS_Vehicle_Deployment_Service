package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

/**
 * Abstract class representing metadata for entities.
 */
@Getter
@Setter
@RequiredArgsConstructor
@MappedSuperclass
public abstract class MetaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @CreationTimestamp
    private Date creationDate;
    @UpdateTimestamp
    private Date lastModifiedDate;

    @Version
    private Long version;

    private boolean isActive = true;
}
