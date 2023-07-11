package com.hrms.Hrmsbackend.repo;

import com.hrms.Hrmsbackend.models.UserContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContactRepo extends JpaRepository<UserContactEntity,String> {
}
