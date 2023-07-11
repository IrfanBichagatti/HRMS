package com.hrms.Hrmsbackend.repo;

import com.hrms.Hrmsbackend.models.DocsManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocsManagerRepo extends JpaRepository<DocsManagerEntity, String>{
}
