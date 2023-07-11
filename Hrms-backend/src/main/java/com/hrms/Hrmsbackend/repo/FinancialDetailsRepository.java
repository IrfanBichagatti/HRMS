package com.hrms.Hrmsbackend.repo;

import com.hrms.Hrmsbackend.models.FinancialDetails;
import com.hrms.Hrmsbackend.models.ProfessionalDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialDetailsRepository extends JpaRepository<FinancialDetails,String> {
}
