package com.hrms.Hrmsbackend.repo;

import com.hrms.Hrmsbackend.models.UserAttendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userAttendanceRepo extends JpaRepository<UserAttendance,Integer> {

}
