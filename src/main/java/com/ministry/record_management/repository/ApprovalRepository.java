package com.ministry.record_management.repository;

import com.ministry.record_management.enums.ApprovalStatus;
import com.ministry.record_management.model.Approval;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApprovalRepository extends JpaRepository<Approval, Long>{

    List<Approval> findByApprovalStatus(ApprovalStatus approvalStatus);
}
