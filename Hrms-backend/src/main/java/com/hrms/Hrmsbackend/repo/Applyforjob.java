package com.hrms.Hrmsbackend.repo;

import com.hrms.Hrmsbackend.models.UserApplyJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Applyforjob extends JpaRepository <UserApplyJob,Integer>{
}
