package com.hrms.Hrmsbackend.repo;

import com.hrms.Hrmsbackend.models.CollaborationEntity;
import com.hrms.Hrmsbackend.models.FinancialDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollabRepo extends JpaRepository<CollaborationEntity,Integer> {
}
