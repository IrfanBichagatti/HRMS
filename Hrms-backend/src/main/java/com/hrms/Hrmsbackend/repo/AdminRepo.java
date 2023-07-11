package com.hrms.Hrmsbackend.repo;

import com.hrms.Hrmsbackend.models.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepo extends JpaRepository<AdminEntity,Long> {
    AdminEntity findByEmail(String email);


}
