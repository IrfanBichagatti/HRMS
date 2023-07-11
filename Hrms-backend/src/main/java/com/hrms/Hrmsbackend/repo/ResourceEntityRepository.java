package com.hrms.Hrmsbackend.repo;

import com.hrms.Hrmsbackend.models.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceEntityRepository extends JpaRepository<ResourceEntity, String> {
}
