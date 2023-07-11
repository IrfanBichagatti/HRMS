package com.hrms.Hrmsbackend.repo;

import com.hrms.Hrmsbackend.models.OnboardingAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnboardingAdminRepo extends JpaRepository<OnboardingAdmin,String> {
}
