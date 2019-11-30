package com.beornot2be.docsEE.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionTypeRepository extends JpaRepository<PermissionType, Integer> {
    public Optional<PermissionType> findById(Integer id);
}