package com.example.backend.repository;

import com.example.backend.model.MetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface ApiRepository<T extends MetaData> extends JpaRepository<T, Long> {
    List<T> findByIsActiveTrue();

    Optional<T> findByIdAndIsActiveTrue(Long id);
}
