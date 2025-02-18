package com.example.EMS_backend.Controller;

import com.example.EMS_backend.Entity.Employee;
import com.example.EMS_backend.dto.EmployeeAddressDto;
import com.example.EMS_backend.service.impl.EmployeeAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee-address")
public class EmployeeAddressController {

    private final EmployeeAddressService employeeAddressService;

    @Autowired
    public EmployeeAddressController(EmployeeAddressService employeeAddressService) {
        this.employeeAddressService = employeeAddressService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<EmployeeAddressDto> createEmployeeAddress(@PathVariable("id") Long id,
                                                                    @RequestBody EmployeeAddressDto employeeAddressDto) {
        employeeAddressDto.setEmployeeId(id);
        EmployeeAddressDto createdAddress = employeeAddressService.createEmployeeAddress(employeeAddressDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAddress);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeAddressDto> updateEmployeeAddress(@PathVariable("id") Long addressId, @RequestBody EmployeeAddressDto employeeAddressDto) {
        EmployeeAddressDto updatedAddress = employeeAddressService.updateEmployeeAddress(addressId, employeeAddressDto);
        return ResponseEntity.ok(updatedAddress);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeAddressDto> getEmployeeAddressById(@PathVariable("id") Long addressId) {
        EmployeeAddressDto address = employeeAddressService.getEmployeeAddressById(addressId);
        return ResponseEntity.ok(address);
    }

    @GetMapping
    public ResponseEntity<Page<EmployeeAddressDto>> getAllEmployeeAddresses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ASC") String sortDirection) {

        Sort sort = Sort.by(
                Sort.Direction.fromString(sortDirection), "country"
        ).and(Sort.by(
                Sort.Direction.fromString(sortDirection), "state"
        )).and(Sort.by(
                Sort.Direction.fromString(sortDirection), "city"
        ));

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<EmployeeAddressDto> employeeAddresses = employeeAddressService.getAllEmployeeAddresses(pageable);
        return ResponseEntity.ok(employeeAddresses);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeAddress(@PathVariable("id") Long addressId) {
        employeeAddressService.deleteEmployeeAddress(addressId);
        return ResponseEntity.ok("Address deleted successfully");
    }
    @GetMapping("/search")
    public ResponseEntity<Page<EmployeeAddressDto>> searchEmployeeAddresses(
            @RequestParam(required = false) String area,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String country,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "country") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection) {

        if (area != null) area = area.toUpperCase();
        if (city != null) city = city.toUpperCase();
        if (state != null) state = state.toUpperCase();
        if (country != null) country = country.toUpperCase();

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), "country", "state", "city");
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<EmployeeAddressDto> employeeAddresses = employeeAddressService.searchEmployeeAddresses(area, city, state, country, pageable);

        return ResponseEntity.ok(employeeAddresses);

}
}
