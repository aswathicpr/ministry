package com.ministry.record_management.repository;

import com.ministry.record_management.model.Document;
import com.ministry.record_management.model.DocumentWorkflow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentWorkFlowRepository extends JpaRepository<DocumentWorkflow, Long>{

    List<DocumentWorkflow> findByDocument(Document document);

}
