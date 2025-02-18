package com.example.EMS_backend.dto;

import com.example.EMS_backend.Entity.EmployeeAddress;

public class EmployeeAddressDto {
    private Long employeeId;
    private String buildingName;
    private String area;
    private String city;
    private String state;
    private String country;
    private String pincode;

    public EmployeeAddressDto(EmployeeAddress address) {
        this.employeeId = address.getEmployee().getId();
        this.buildingName = address.getBuildingName();
        this.area = address.getArea();
        this.city = address.getCity();
        this.state = address.getState();
        this.country = address.getCountry();
        this.pincode = address.getPincode();
    }
    public EmployeeAddressDto(){

    }
    public EmployeeAddressDto(String buildingName, String area, String city, String state, String country, String pincode) {
        this.buildingName = buildingName;
        this.area = area;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
    }


    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }



    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}

