package com.example.EMS_backend.service.impl;

import com.example.EMS_backend.Entity.Employee;
import com.example.EMS_backend.Mapper.EmployeeMapper;
import com.example.EMS_backend.dto.EmployeeDto;
import com.example.EMS_backend.exception.ResourceNotFoundException;
import com.example.EMS_backend.repository.EmployeeRepository;
import com.example.EMS_backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
       Employee employee= EmployeeMapper.mapToEmployee(employeeDto);
      Employee savedEmployee= employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto((savedEmployee));
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        try {
            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with the given id : " + employeeId));
            return EmployeeMapper.mapToEmployeeDto(employee);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Employee does not exist with the given id : " + employeeId);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching the employee", e);
        }
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        try {
            List<Employee> employees = employeeRepository.findAll();
            return employees.stream()
                    .map(employee -> EmployeeMapper.mapToEmployeeDto(employee))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching the employees", e);
        }
    }


    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployee) {
        try {
            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with the given id: " + employeeId));

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

            employeeRepository.deleteById(employeeId);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Employee does not exist with the given id: " + employeeId);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting the employee", e);
        }
    }


}
