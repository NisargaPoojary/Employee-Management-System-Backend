
package com.example.EMS_backend.service.impl;

import com.example.EMS_backend.Entity.Employee;
import com.example.EMS_backend.Mapper.EmployeeMapper;
import com.example.EMS_backend.dto.EmployeeDto;
import com.example.EMS_backend.exception.ResourceNotFoundException;
import com.example.EMS_backend.repository.EmployeeRepository;
import com.example.EMS_backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        employee.setDeleted(false);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with the given id: " + employeeId));

        if (employee.isDeleted()) {
            throw new ResourceNotFoundException("Employee does not exist with the given id: " + employeeId);
        }

        return EmployeeMapper.mapToEmployeeDto(employee);
    }


    @Override
    public List<EmployeeDto> getAllEmployees(int page, int size, String sortDir, String sortBy) {
        Sort.Direction direction = sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        List<String> validFields = List.of("id", "firstName", "lastName", "email");
        if (!validFields.contains(sortBy)) {
            throw new IllegalArgumentException("Invalid sort field: " + sortBy);
        }

        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Employee> employeePage = employeeRepository.findByDeletedFalse(pageable);

        return employeePage.stream()
                .map(EmployeeMapper::mapToEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployee) {
        try {
            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with the given id: " + employeeId));

            if (employee.isDeleted()) {
                throw new ResourceNotFoundException("Employee does not exist with the given id: " + employeeId);
            }


            employee.setFirstName(updateEmployee.getFirstName());
            employee.setLastName(updateEmployee.getLastName());
            employee.setEmail(updateEmployee.getEmail());

            Employee updatedEmployeeObj = employeeRepository.save(employee);

            return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Employee does not exist with the given id: " + employeeId);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating the employee", e);
        }
    }


    @Override
    public void deleteEmployee(Long employeeId) {
        try {
            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with the given id: " + employeeId));
            employee.setDeleted(true);
            employeeRepository.save(employee);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Employee does not exist with the given id: " + employeeId);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting the employee", e);
        }
    }
    @Override
    public EmployeeDto partialUpdateEmployee(Long employeeId, EmployeeDto partialUpdate) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with the given id: " + employeeId));

        if (employee.isDeleted()) {
            throw new ResourceNotFoundException("Employee does not exist with the given id: " + employeeId);
        }

        if (partialUpdate.getFirstName() != null) {
            employee.setFirstName(partialUpdate.getFirstName());
        }
        if (partialUpdate.getLastName() != null) {
            employee.setLastName(partialUpdate.getLastName());
        }
        if (partialUpdate.getEmail() != null) {
            employee.setEmail(partialUpdate.getEmail());
        }

        Employee updatedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
    }




}
