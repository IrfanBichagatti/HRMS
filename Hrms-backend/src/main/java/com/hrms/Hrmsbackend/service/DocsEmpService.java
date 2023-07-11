package com.hrms.Hrmsbackend.service;
import com.hrms.Hrmsbackend.models.DocsEmpEntity;
import com.hrms.Hrmsbackend.repo.DocsEmpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;
@Service
public class DocsEmpService {
    @Autowired
    private DocsEmpRepo docsEmpRepo;


    public DocsEmpEntity store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        DocsEmpEntity docsEmpEntity = new DocsEmpEntity(fileName, file.getContentType(), file.getBytes());

        return docsEmpRepo.save(docsEmpEntity);
    }

    public DocsEmpEntity getFile(String id) {
        return docsEmpRepo.findById(id).get();
    }

    public Stream<DocsEmpEntity> getAllFiles() {return docsEmpRepo.findAll().stream();
    }





}
