package com.hrms.Hrmsbackend.repo;

import com.hrms.Hrmsbackend.models.AlertData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepo extends JpaRepository<AlertData,String> {

}
