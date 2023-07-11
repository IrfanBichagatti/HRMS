package com.hrms.Hrmsbackend.repo;

import com.hrms.Hrmsbackend.models.AdminApprovalEntitytwo;
import com.hrms.Hrmsbackend.models.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminFinanceRepo extends JpaRepository<AdminApprovalEntitytwo,String> {
}
