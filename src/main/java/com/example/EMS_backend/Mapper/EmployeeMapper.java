package com.example.EMS_backend.Mapper;

import com.example.EMS_backend.Entity.Employee;
import com.example.EMS_backend.Entity.EmployeeAddress;
import com.example.EMS_backend.dto.EmployeeAddressDto;
import com.example.EMS_backend.dto.EmployeeDto;

public class EmployeeMapper {

    public static EmployeeDto mapToEmployeeDto(Employee employee) {
        EmployeeAddressDto addressDto = null;
        if (employee.getAddress() != null) {
            addressDto = new EmployeeAddressDto(
                    employee.getAddress().getBuildingName(),
                    employee.getAddress().getArea(),
                    employee.getAddress().getCity(),
                    employee.getAddress().getState(),
                    employee.getAddress().getCountry(),
                    employee.getAddress().getPincode()
            );
        }

        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                addressDto
        );
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());

        if (employeeDto.getAddress() != null) {
            EmployeeAddress address = new EmployeeAddress();
            address.setBuildingName(employeeDto.getAddress().getBuildingName());
            address.setArea(employeeDto.getAddress().getArea());
            address.setCity(employeeDto.getAddress().getCity());
            address.setState(employeeDto.getAddress().getState());
            address.setCountry(employeeDto.getAddress().getCountry());
            address.setPincode(employeeDto.getAddress().getPincode());
            address.setEmployee(employee);  // Set back the employee reference for the address
            employee.setAddress(address);
        }

        return employee;
    }

    public static EmployeeAddressDto mapToEmployeeAddressDto(Employee employee) {
        if (employee.getAddress() == null) {
            return null; // Handle the case where there's no address
        }

        EmployeeAddress address = employee.getAddress();
        return new EmployeeAddressDto(
                address.getBuildingName(),
                address.getArea(),
                address.getCity(),
                address.getState(),
                address.getCountry(),
                address.getPincode()
        );
    }

}
