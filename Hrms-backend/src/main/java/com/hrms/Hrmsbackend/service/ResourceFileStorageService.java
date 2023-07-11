package com.hrms.Hrmsbackend.service;

import com.hrms.Hrmsbackend.models.ResourceEntity;
import com.hrms.Hrmsbackend.repo.ResourceEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class ResourceFileStorageService {
    @Autowired
    private ResourceEntityRepository resourceEntityRepository;

    public ResourceEntity store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        ResourceEntity resourceEntity = new ResourceEntity(fileName, file.getContentType(), file.getBytes());

        return resourceEntityRepository.save(resourceEntity);
    }

    public ResourceEntity getFile(String id) {
        return resourceEntityRepository.findById(id).get();
    }

    public Stream<ResourceEntity> getAllFiles() {
        return resourceEntityRepository.findAll().stream();
    }
}
