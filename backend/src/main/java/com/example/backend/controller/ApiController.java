package com.example.backend.controller;

import com.example.backend.model.MetaData;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public abstract class ApiController<T extends MetaData, I, O> {
    private final JpaRepository<T, String> apiRepository;
    private final Class<T> entityClass;
    private final Class<O> outputDtoClass;

    @Autowired
    private ModelMapper modelMapper;

    protected ApiController(JpaRepository<T, String> apiRepository, Class<T> entityClass, Class<O> outputDtoClass) {
        this.apiRepository = apiRepository;
        this.entityClass = entityClass;
        this.outputDtoClass = outputDtoClass;
    }

    private O convertToDto(T entity) {
        return modelMapper.map(entity, outputDtoClass);
    }

    private T convertToEntity(I inputDto) {
        return modelMapper.map(inputDto, entityClass);
    }

    @GetMapping
    public ResponseEntity<List<O>> getAllEntries() {
        List<T> allEntries = apiRepository.findAll();
        if (allEntries.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        List<O> outputDtos = allEntries.stream()
                .map(this::convertToDto)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(outputDtos);
    }

    @PostMapping
    public ResponseEntity<O> addEntry(@RequestBody I inputDto) {
        try {
            T entry = convertToEntity(inputDto);
            T newEntry = apiRepository.save(entry);
            O outputDto = convertToDto(newEntry);
            return ResponseEntity.status(HttpStatus.CREATED).body(outputDto);
        } catch (HttpMessageNotReadableException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping
    public ResponseEntity<Void> updateEntry(@RequestBody I inputDto) {
        T updatedEntry = convertToEntity(inputDto);
        if (apiRepository.existsById(updatedEntry.getId())) {
            apiRepository.save(updatedEntry);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping
    public ResponseEntity<T> deleteEntry(@RequestParam String id) {
        if (apiRepository.existsById(id)) {
            Optional<T> entryToDelete = apiRepository.findById(id);
            apiRepository.deleteById(id);
            if(entryToDelete.isPresent())
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            else
                return ResponseEntity.status(HttpStatus.OK).body(entryToDelete.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}