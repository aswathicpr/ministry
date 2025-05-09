package com.ministry.record_management.model;

import java.sql.Date;
import java.time.LocalDate;

import com.ministry.record_management.enums.ApprovalStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor

public class Approval {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private DocumentWorkflow documentWorkflow;

    @ManyToOne
    private Department approverDepartment;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;

    private LocalDate approvalDate;
    private String approverId;
}
