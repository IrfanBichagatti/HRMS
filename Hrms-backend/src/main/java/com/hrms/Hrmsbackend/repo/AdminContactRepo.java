package com.hrms.Hrmsbackend.repo;

import com.hrms.Hrmsbackend.models.AdminApproveContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminContactRepo extends JpaRepository<AdminApproveContactEntity,String> {
}
