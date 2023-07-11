package com.hrms.Hrmsbackend.repo;

import com.hrms.Hrmsbackend.models.DocsEmpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface DocsEmpRepo extends JpaRepository<DocsEmpEntity, String> {

}
