package com.example.EMS_backend.dto;

public class EmployeeRatingDto {
    private Long employeeId;
    private int rating;

    public EmployeeRatingDto() {}

    public EmployeeRatingDto(Long employeeId, int rating) {
        this.employeeId = employeeId;
        this.rating = rating;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
