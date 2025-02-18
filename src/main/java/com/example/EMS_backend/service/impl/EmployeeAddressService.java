package com.example.EMS_backend.service.impl;

import com.example.EMS_backend.Entity.Employee;
import com.example.EMS_backend.Entity.EmployeeAddress;
import com.example.EMS_backend.Mapper.EmployeeMapper;
import com.example.EMS_backend.dto.EmployeeAddressDto;
import com.example.EMS_backend.repository.EmployeeAddressRepository;
import com.example.EMS_backend.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeAddressService {
    @Autowired
    private EmployeeAddressRepository employeeAddressRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public EmployeeAddressDto createEmployeeAddress(EmployeeAddressDto employeeAddressDto) {
        EmployeeAddress address = new EmployeeAddress();

        Employee employee = employeeRepository.findById(employeeAddressDto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        address.setEmployee(employee);
        address.setBuildingName(employeeAddressDto.getBuildingName());
        address.setArea(employeeAddressDto.getArea());
        address.setCity(employeeAddressDto.getCity());
        address.setState(employeeAddressDto.getState());
        address.setCountry(employeeAddressDto.getCountry());
        address.setPincode(employeeAddressDto.getPincode());

        EmployeeAddress savedAddress = employeeAddressRepository.save(address);

        return new EmployeeAddressDto(savedAddress);
    }

    public Page<EmployeeAddressDto> getAllEmployeeAddresses(Pageable pageable) {
        Page<Employee> employees = employeeRepository.findAll(pageable);
        return employees.map(employee -> EmployeeMapper.mapToEmployeeAddressDto(employee));
    }


    public EmployeeAddressDto updateEmployeeAddress(Long addressId, EmployeeAddressDto employeeAddressDto) {
        EmployeeAddress existingAddress = employeeAddressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        existingAddress.setBuildingName(employeeAddressDto.getBuildingName());
        existingAddress.setArea(employeeAddressDto.getArea());
        existingAddress.setCity(employeeAddressDto.getCity());
        existingAddress.setState(employeeAddressDto.getState());
        existingAddress.setCountry(employeeAddressDto.getCountry());
        existingAddress.setPincode(employeeAddressDto.getPincode());

        EmployeeAddress updatedAddress = employeeAddressRepository.save(existingAddress);

        return new EmployeeAddressDto(updatedAddress);
    }

    public EmployeeAddressDto getEmployeeAddressById(Long addressId) {
        EmployeeAddress address = employeeAddressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        return new EmployeeAddressDto(address);
    }

    public void deleteEmployeeAddress(Long addressId) {
        EmployeeAddress address = employeeAddressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        employeeAddressRepository.delete(address);
    }
    @Transactional
    public Page<EmployeeAddressDto> searchEmployeeAddresses(String area, String city, String state, String country, Pageable pageable) {
        Page<EmployeeAddress> employeeAddresses = employeeAddressRepository.searchEmployeeAddresses(area, city, state, country, pageable);

        return employeeAddresses.map(address -> new EmployeeAddressDto(address));
      }
}
