package com.example.EMS_backend.repository;

import com.example.EMS_backend.Entity.EmployeeAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeAddressRepository extends JpaRepository<EmployeeAddress, Long> {
    @Query("SELECT e FROM EmployeeAddress e WHERE " +
            "(:area IS NULL OR e.area LIKE %:area%) AND " +
            "(:city IS NULL OR e.city LIKE %:city%) AND " +
            "(:state IS NULL OR e.state LIKE %:state%) AND " +
            "(:country IS NULL OR e.country LIKE %:country%)")
    Page<EmployeeAddress> searchEmployeeAddresses(@Param("area") String area,
                                                  @Param("city") String city,
                                                  @Param("state") String state,
                                                  @Param("country") String country,
                                                  Pageable pageable);
}
