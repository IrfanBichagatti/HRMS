package com.hrms.Hrmsbackend.repo;

import com.hrms.Hrmsbackend.models.OnboardingDocEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnboardingDocRepo extends JpaRepository<OnboardingDocEntity,String> {
}
