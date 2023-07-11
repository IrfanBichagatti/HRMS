package com.hrms.Hrmsbackend.controller;

import com.hrms.Hrmsbackend.models.AdminBroadcastEntity;
import com.hrms.Hrmsbackend.service.BroadcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
public class BroadcastController {

    @Autowired
    BroadcastService broadcastService;

    //admin post broadcast
    @PostMapping("/adminBroadcast")
    public AdminBroadcastEntity adminBroadcastMsg(@RequestBody AdminBroadcastEntity adminBroadcastEntity)
    {
        return broadcastService.postBroadcast(adminBroadcastEntity);
    }

    //user get broadcast
    @GetMapping("/getBroadcastMsg")
    public List<AdminBroadcastEntity> getBroadcast()
    {
        return broadcastService.broadcastMsg();
    }


}
