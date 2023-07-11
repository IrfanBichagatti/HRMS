package com.hrms.Hrmsbackend.repo;

import com.hrms.Hrmsbackend.models.UserRegularizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRegularizationRepo extends JpaRepository<UserRegularizationEntity,Integer> {
}
