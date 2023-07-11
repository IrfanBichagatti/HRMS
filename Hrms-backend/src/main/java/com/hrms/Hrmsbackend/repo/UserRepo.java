package com.hrms.Hrmsbackend.repo;

import com.hrms.Hrmsbackend.models.UserAttendance;
import com.hrms.Hrmsbackend.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<UserEntity,String> {


}
