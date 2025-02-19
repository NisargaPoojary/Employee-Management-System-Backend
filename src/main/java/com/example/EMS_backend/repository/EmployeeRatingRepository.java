package com.example.EMS_backend.repository;

import com.example.EMS_backend.Entity.EmployeeRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRatingRepository extends JpaRepository<EmployeeRating,Long> {
    Optional<EmployeeRating> findByEmployeeId(Long employeeId);
}
