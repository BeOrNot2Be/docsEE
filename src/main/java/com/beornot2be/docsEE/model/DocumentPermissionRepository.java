package com.beornot2be.docsEE.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentPermissionRepository extends JpaRepository<DocumentPermission, Integer> {
    public Optional<DocumentPermission> findById(Integer id);
}