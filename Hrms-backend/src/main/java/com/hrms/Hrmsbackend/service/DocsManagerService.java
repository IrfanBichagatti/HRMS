package com.hrms.Hrmsbackend.service;

import com.hrms.Hrmsbackend.models.DocsManagerEntity;
import com.hrms.Hrmsbackend.repo.DocsManagerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;
@Service
public class DocsManagerService {

    @Autowired
    private DocsManagerRepo docsManagerRepo;


    public DocsManagerEntity store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        DocsManagerEntity docsManagerEntity = new DocsManagerEntity(fileName, file.getContentType(), file.getBytes());

        return docsManagerRepo.save(docsManagerEntity);
    }

    public DocsManagerEntity getFile(String id) {return docsManagerRepo.findById(id).get();
    }

    public Stream<DocsManagerEntity> getAllFiles() {return docsManagerRepo.findAll().stream();
    }
}
