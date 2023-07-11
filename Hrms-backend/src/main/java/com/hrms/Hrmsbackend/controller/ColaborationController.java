package com.hrms.Hrmsbackend.controller;

import com.hrms.Hrmsbackend.models.CollaborationEntity;
import com.hrms.Hrmsbackend.models.QuizEntity;
import com.hrms.Hrmsbackend.service.Collabservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class ColaborationController {

    @Autowired
    private Collabservice collabservice;

    @PostMapping("/postaquiz")
    public QuizEntity addquiize(@RequestBody QuizEntity quizEntity){
        return  collabservice.addquiz(quizEntity);
    }
    @GetMapping("/getallQuize")
    public List<QuizEntity> getallquize()
    {
        return collabservice.getallquize();
    }

    @PostMapping("/postcontent")
    public CollaborationEntity collaborationEntity(@RequestBody CollaborationEntity collaborationEntity)
    {
        return collabservice.postcontent(collaborationEntity);
    }
    @GetMapping("/getallpost")
    public List<CollaborationEntity> getallcollpost()
    {
        return collabservice.getallpost();
    }
}
