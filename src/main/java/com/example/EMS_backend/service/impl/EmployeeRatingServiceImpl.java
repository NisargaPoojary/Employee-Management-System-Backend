package com.example.EMS_backend.service.impl;

import com.example.EMS_backend.Entity.Employee;
import com.example.EMS_backend.Entity.EmployeeRating;
import com.example.EMS_backend.dto.EmployeeRatingDto;
import com.example.EMS_backend.exception.ResourceNotFoundException;
import com.example.EMS_backend.repository.EmployeeRatingRepository;
import com.example.EMS_backend.repository.EmployeeRepository;
import com.example.EMS_backend.service.EmployeeRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeRatingServiceImpl implements EmployeeRatingService {

    @Autowired
    private EmployeeRatingRepository employeeRatingRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeRatingDto rateEmployee(Long employeeId, int rating) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + employeeId));

        // Check if rating already exists for the employee
        if (employeeRatingRepository.findByEmployeeId(employeeId).isPresent()) {
            throw new IllegalArgumentException("Employee already has a rating. Please update it instead.");
        }

        EmployeeRating employeeRating = new EmployeeRating(employee, rating);
        employeeRatingRepository.save(employeeRating);

        return new EmployeeRatingDto(employeeId, rating);
    }

    @Override
    public EmployeeRatingDto getEmployeeRating(Long employeeId) {
        EmployeeRating employeeRating = employeeRatingRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found for Employee ID: " + employeeId));

        return new EmployeeRatingDto(employeeId, employeeRating.getRating());
    }

    @Override
    public EmployeeRatingDto updateEmployeeRating(Long employeeId, int rating) {
        EmployeeRating employeeRating = employeeRatingRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found for Employee ID: " + employeeId));

        employeeRating.setRating(rating);
        employeeRatingRepository.save(employeeRating);

        return new EmployeeRatingDto(employeeId, rating);
    }

    @Override
    public List<EmployeeRatingDto> getAllRatings() {
        return employeeRatingRepository.findAll().stream()
                .map(rating -> new EmployeeRatingDto(rating.getEmployee().getId(), rating.getRating()))
                .collect(Collectors.toList());
    }
}
