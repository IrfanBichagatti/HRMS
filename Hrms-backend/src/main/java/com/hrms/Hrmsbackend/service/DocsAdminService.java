package com.hrms.Hrmsbackend.service;

import com.hrms.Hrmsbackend.models.DocsAdminEntity;
import com.hrms.Hrmsbackend.repo.DocsAdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class DocsAdminService {
    @Autowired
    private DocsAdminRepo docsAdminRepo;


    public DocsAdminEntity store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        DocsAdminEntity docsAdminEntity = new DocsAdminEntity(fileName, file.getContentType(), file.getBytes());

        return docsAdminRepo.save(docsAdminEntity);
    }

    public DocsAdminEntity getFile(String id) {
        return docsAdminRepo.findById(id).get();
    }

    public Stream<DocsAdminEntity> getAllFiles() {return docsAdminRepo.findAll().stream();
    }
}
