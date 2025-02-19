package com.example.EMS_backend.service;

import com.example.EMS_backend.dto.EmployeeRatingDto;

import java.util.List;

public interface EmployeeRatingService {
    EmployeeRatingDto rateEmployee(Long employeeId, int rating);
    EmployeeRatingDto getEmployeeRating(Long employeeId);
    EmployeeRatingDto updateEmployeeRating(Long employeeId, int rating);
    List<EmployeeRatingDto> getAllRatings();
}
