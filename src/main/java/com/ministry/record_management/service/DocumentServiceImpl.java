package com.ministry.record_management.service;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ministry.record_management.enums.DocumentType;
import com.ministry.record_management.enums.SecurityClassification;
import com.ministry.record_management.enums.WorkflowState;
import com.ministry.record_management.model.Document;
import com.ministry.record_management.model.DocumentWorkflow;
import com.ministry.record_management.repository.DepartmentRepository;
import com.ministry.record_management.repository.DocumentRepository;

@Service

public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepo;
    @Autowired
    private DepartmentRepository deptRepo;

    @Override
    public List<Document> searchDocuments(String number, String title, String deptCode, DocumentType type,
            SecurityClassification classification, WorkflowState state, LocalDate startDate, LocalDate endDate) {

        Specification<Document> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (number != null && !number.isBlank()) {
                predicates.add(cb.equal(root.get("documentNumber"), number));
            }

            if (title != null && !title.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
            }

            if (deptCode != null && !deptCode.isBlank()) {
                predicates.add(cb.equal(root.join("ownerDepartment").get("departmentCode"), deptCode));
            }

            if (type != null) {
                predicates.add(cb.equal(root.get("documentType"), type));
            }

            if (classification != null) {
                predicates.add(cb.equal(root.get("securityClassification"), classification));
            }

            if (startDate != null && endDate != null) {
                predicates.add(cb.between(root.get("creationDate"), startDate, endDate));
            }

            if (state != null) {
                Subquery<Long> subquery = query.subquery(Long.class);
                Root<DocumentWorkflow> workflowRoot = subquery.from(DocumentWorkflow.class);
                subquery.select(workflowRoot.get("document").get("id"))
                        .where(cb.equal(workflowRoot.get("currentState"), state));

                predicates.add(root.get("id").in(subquery));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return documentRepo.findAll(spec);
    }

}
