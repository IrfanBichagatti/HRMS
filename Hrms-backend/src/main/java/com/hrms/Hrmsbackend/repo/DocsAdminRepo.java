package com.hrms.Hrmsbackend.repo;

import com.hrms.Hrmsbackend.models.DocsAdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocsAdminRepo extends JpaRepository<DocsAdminEntity, String> {
}
