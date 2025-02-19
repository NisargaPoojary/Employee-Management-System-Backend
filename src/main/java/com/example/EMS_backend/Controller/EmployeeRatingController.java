package com.example.EMS_backend.Controller;

import com.example.EMS_backend.dto.EmployeeRatingDto;
import com.example.EMS_backend.service.EmployeeRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class EmployeeRatingController {

    @Autowired
    private EmployeeRatingService employeeRatingService;

    // Manager creates or updates a rating
    @PostMapping("/{employeeId}/{rating}")
    public EmployeeRatingDto rateEmployee(
            @PathVariable Long employeeId,
            @PathVariable int rating,
            @RequestParam String role) {

        // Checking if the role is manager
        if (!role.equals("manager")) {
            throw new IllegalArgumentException("Only managers can create or update ratings.");
        }

        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }

        return employeeRatingService.rateEmployee(employeeId, rating);
    }

    // Employee views their own rating
    @GetMapping("/{employeeId}")
    public EmployeeRatingDto getEmployeeRating(
            @PathVariable Long employeeId,
            @RequestParam String role) {

        // Check if the role is employee or manager
        if (!role.equals("employee") && !role.equals("manager")) {
            throw new IllegalArgumentException("Invalid role");
        }

        return employeeRatingService.getEmployeeRating(employeeId);
    }

    // Manager updates rating
    @PutMapping("/{employeeId}/{rating}")
    public EmployeeRatingDto updateEmployeeRating(
            @PathVariable Long employeeId,
            @PathVariable int rating,
            @RequestParam String role) {

        // Check if the role is manager
        if (!role.equals("manager")) {
            throw new IllegalArgumentException("Only managers can update ratings.");
        }

        return employeeRatingService.updateEmployeeRating(employeeId, rating);
    }

    // Manager views all ratings
    @GetMapping
    public List<EmployeeRatingDto> getAllRatings(@RequestParam String role) {

        // Check if the role is manager
        if (!role.equals("manager")) {
            throw new IllegalArgumentException("Only managers can view all ratings.");
        }

        return employeeRatingService.getAllRatings();
    }
}
