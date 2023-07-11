package com.hrms.Hrmsbackend.repo;

import com.hrms.Hrmsbackend.models.AdminApproveProfessionalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminProfessionalRepo extends JpaRepository<AdminApproveProfessionalEntity,String> {

}
