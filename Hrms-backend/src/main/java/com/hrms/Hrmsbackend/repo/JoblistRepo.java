package com.hrms.Hrmsbackend.repo;


import com.hrms.Hrmsbackend.models.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoblistRepo extends JpaRepository<JobEntity,Integer> {
}