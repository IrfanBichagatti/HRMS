package com.hrms.Hrmsbackend.repo;

import com.hrms.Hrmsbackend.models.UserLeaveDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserLeaveDetailsRepo extends JpaRepository<UserLeaveDetails,Integer> {

    UserLeaveDetails findByLeaveType(String leavetype);
}
