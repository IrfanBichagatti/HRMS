package com.hrms.Hrmsbackend.repo;

import com.hrms.Hrmsbackend.models.AvgAttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvgAttendanceRepo extends JpaRepository<AvgAttendanceEntity,String> {
}
