package com.hrms.Hrmsbackend.repo;

import com.hrms.Hrmsbackend.models.OnboardingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnboardingRepo extends JpaRepository<OnboardingEntity, String> {
}
