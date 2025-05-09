package com.ministry.record_management.model;

import java.sql.Date;
import java.time.LocalDate;

import com.ministry.record_management.enums.DocumentType;
import com.ministry.record_management.enums.SecurityClassification;

import jakarta.persistence.Column;
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
public class Document {
   @Id 
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String documentNumber;

    private String title;
    private String description;
    private LocalDate creationDate;

    @ManyToOne
    private Department ownerDepartment;

    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    @Enumerated(EnumType.STRING)
    private SecurityClassification securityClassification;

    private String digitalFileLocation;
    private String version; 
    
}
