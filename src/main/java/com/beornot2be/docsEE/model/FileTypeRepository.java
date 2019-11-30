package com.beornot2be.docsEE.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface FileTypeRepository extends JpaRepository<FileType, Integer> {
    public Optional<FileType> findById(Integer id);
}

