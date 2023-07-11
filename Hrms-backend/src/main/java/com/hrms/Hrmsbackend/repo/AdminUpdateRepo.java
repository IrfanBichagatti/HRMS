package com.hrms.Hrmsbackend.repo;

import com.hrms.Hrmsbackend.models.AdminApprovalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminUpdateRepo extends JpaRepository<AdminApprovalEntity,String> {
}
