package com.example.EMS_backend.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_ratings")
public class EmployeeRating {

    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @MapsId
    private Employee employee;

    @Column(nullable = false)
    private int rating;

    public EmployeeRating() {}

    public EmployeeRating(Employee employee, int rating) {
        this.employee = employee;
        this.rating = rating;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        this.rating = rating;
    }
}
