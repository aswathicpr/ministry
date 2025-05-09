package com.ministry.record_management.service;

import java.time.LocalDate;
import java.util.List;

import com.ministry.record_management.enums.DocumentType;
import com.ministry.record_management.enums.SecurityClassification;
import com.ministry.record_management.enums.WorkflowState;
import com.ministry.record_management.model.Document;

public interface DocumentService {

    List<Document> searchDocuments(String number, String title, String deptCode, DocumentType type,
            SecurityClassification classification, WorkflowState state, LocalDate startDate, LocalDate endDate);

}
