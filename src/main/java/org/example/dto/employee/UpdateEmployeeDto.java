package org.example.dto.employee;

import org.example.data.entity.enums.DriverType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class UpdateEmployeeDto {
    @Positive(message = "Employee ID must be positive!")
    private long id;

    @NotBlank(message = "Employee name cannot be blank!")
    @Size(max = 100, message = "Employee name can have up to 100 characters!")
    private String name;

    @Positive(message = "Salary must be positive!")
    private double salary;

    @NotNull(message = "Driver type cannot be null!")
    private DriverType driverType;

    public UpdateEmployeeDto(long id, String name, double salary, DriverType driverType) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.driverType = driverType;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public DriverType getDriverType() { return driverType; }
    public void setDriverType(DriverType driverType) { this.driverType = driverType; }
}
