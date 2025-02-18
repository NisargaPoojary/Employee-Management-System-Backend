
package com.example.EMS_backend.repository;

import com.example.EMS_backend.Entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Page<Employee> findByDeletedFalse(Pageable pageable);

    Optional<Employee> findActiveEmployeeById(@Param("id") Long id);

}
