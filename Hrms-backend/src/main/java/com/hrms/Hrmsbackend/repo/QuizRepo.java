package com.hrms.Hrmsbackend.repo;

import com.hrms.Hrmsbackend.models.CollaborationEntity;
import com.hrms.Hrmsbackend.models.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepo  extends JpaRepository<QuizEntity,Integer> {
}
