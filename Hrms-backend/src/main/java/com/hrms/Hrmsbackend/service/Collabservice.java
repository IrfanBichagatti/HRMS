package com.hrms.Hrmsbackend.service;

import com.hrms.Hrmsbackend.models.CollaborationEntity;
import com.hrms.Hrmsbackend.models.QuizEntity;
import com.hrms.Hrmsbackend.repo.CollabRepo;
import com.hrms.Hrmsbackend.repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class Collabservice {
    @Autowired
    private CollabRepo collabRepo;
    @Autowired
    private QuizRepo quizRepo;

    public CollaborationEntity postcontent(CollaborationEntity collaborationEntity) {
        return collabRepo.save(collaborationEntity);
    }

    public List<CollaborationEntity> getallpost() {
        List<CollaborationEntity> collaborationEntity = collabRepo.findAll();
        Collections.reverse(collaborationEntity);
        return  collaborationEntity;

    }

    public QuizEntity addquiz(QuizEntity quizEntity) {
        return quizRepo.save(quizEntity);
    }

    public List<QuizEntity> getallquize() {
        List<QuizEntity> quizEntities = quizRepo.findAll();
        Collections.reverse(quizEntities);
        return  quizRepo.findAll();
    }
}
