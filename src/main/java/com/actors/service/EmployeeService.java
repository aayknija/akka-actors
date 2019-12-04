package com.actors.service;

public interface EmployeeService {
    void uploadEmployees(String fileURL) throws Exception;

    void updateEmployees(String fileURL) throws Exception;
}
