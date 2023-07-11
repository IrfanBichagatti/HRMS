package com.hrms.Hrmsbackend.service;

import com.hrms.Hrmsbackend.models.AdminBroadcastEntity;
import com.hrms.Hrmsbackend.repo.AdminBroadcastRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BroadcastService {
    @Autowired
    public AdminBroadcastRepo adminBroadcastRepo;

//post broadcast msg
    public AdminBroadcastEntity postBroadcast(AdminBroadcastEntity adminBroadcastEntity ) {
        return adminBroadcastRepo.save(adminBroadcastEntity);
    }

//get msg
    public List<AdminBroadcastEntity> broadcastMsg()
    {
        List<AdminBroadcastEntity> allmsg=adminBroadcastRepo.findAll();

        Collections.reverse(allmsg);
    return allmsg;
    }

}
