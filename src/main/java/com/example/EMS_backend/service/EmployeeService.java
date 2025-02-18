package com.example.EMS_backend.service;

import com.example.EMS_backend.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long employeeId);

    List<EmployeeDto> getAllEmployees(int page, int size, String sortBy, String sortDir);

    EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployee);

    EmployeeDto partialUpdateEmployee(Long employeeId, EmployeeDto partialUpdate);

    void deleteEmployee(Long employeeId);

}