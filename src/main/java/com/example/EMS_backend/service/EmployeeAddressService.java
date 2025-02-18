package com.example.EMS_backend.service;

import com.example.EMS_backend.dto.EmployeeAddressDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeAddressService {

    EmployeeAddressDto createEmployeeAddress(EmployeeAddressDto employeeAddressDto);

    EmployeeAddressDto getEmployeeAddressById(Long addressId);

    EmployeeAddressDto updateEmployeeAddress(Long addressId, EmployeeAddressDto employeeAddressDto);

    void deleteEmployeeAddress(Long addressId);

    Page<EmployeeAddressDto> getAllEmployees(Pageable pageable);
}
