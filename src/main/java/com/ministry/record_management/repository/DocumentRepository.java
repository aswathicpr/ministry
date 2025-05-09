package com.ministry.record_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ministry.record_management.model.Document;

@Repository

public interface DocumentRepository
        extends JpaRepository<Document, Long>, JpaSpecificationExecutor<Document> {

    @Query("SELECT d FROM Document d WHERE d.ownerDepartment.departmentCode = :code")
    List<Document> findByDepartmentCode(String code);

    List<Document> findByTitleContainingIgnoreCase(String title);

    List<Document> findByDocumentNumberContainingIgnoreCase(String number);
}