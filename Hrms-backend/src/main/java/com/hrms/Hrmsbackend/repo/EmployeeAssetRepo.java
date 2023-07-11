package com.hrms.Hrmsbackend.repo;

import com.hrms.Hrmsbackend.models.EmpoyeeAssets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeAssetRepo extends JpaRepository<EmpoyeeAssets,Integer> {
}
