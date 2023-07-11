package com.hrms.Hrmsbackend.repo;


import com.hrms.Hrmsbackend.models.AdminEntity;
import com.hrms.Hrmsbackend.models.ResignationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResignRepo extends JpaRepository<ResignationEntity,Integer> {
    ResignationEntity findByEmail(String email);
    ResignationEntity findAllByEmail(String email);


}
