package org.example.data.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.example.data.enums.DriverType;
import org.example.data.common.BaseEntityModel;

@Entity
@Table(name = "employees")
public class Employee extends BaseEntityModel {
    @NotBlank
    @Size(max = 100)
    private String name;

    @Positive
    private double salary;

    @Enumerated(EnumType.STRING)
    @Column(name = "driver_type")
    private DriverType driverType;

    @ManyToOne
    // optional. Hibernate will create a default company_id.
    // explicit implementation for customizable column name and constraints.
    @JoinColumn(name = "company_id", nullable = false)
    private TransportCompany company;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public DriverType getDriverType() { return driverType; }
    public void setDriverType(DriverType driverType) { this.driverType = driverType; }

    public TransportCompany getCompany() { return company; }
    public void setCompany(TransportCompany company) { this.company = company; }
}
