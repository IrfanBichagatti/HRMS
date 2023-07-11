package com.hrms.Hrmsbackend.repo;

import com.hrms.Hrmsbackend.models.UserLeaveRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLeaveRequestRepo extends JpaRepository<UserLeaveRequestEntity,Integer> {
}
