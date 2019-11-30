package com.actors.web;

import com.actors.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActorController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/upload")
    public void uploadEmployees(@RequestParam("fileURL") String fileURL) throws Exception {
        employeeService.uploadEmployees(fileURL);
    }
}