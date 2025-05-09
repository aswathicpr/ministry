package com.ministry.record_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.ministry.record_management.enums.DocumentType;
import com.ministry.record_management.enums.SecurityClassification;
import com.ministry.record_management.enums.WorkflowState;
import com.ministry.record_management.model.Document;
import com.ministry.record_management.service.DocumentService;

import java.time.LocalDate;
import java.util.*;

@RestController

@RequestMapping("/api/documents")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @GetMapping("/search")
    public List<Document> search(@RequestParam(required = false) String number,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String departmentCode,
            @RequestParam(required = false) DocumentType type,
            @RequestParam(required = false) SecurityClassification classification,
            @RequestParam(required = false) WorkflowState state,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return documentService.searchDocuments(number, title, departmentCode, type, classification, state, startDate,
                endDate);
    }

}
