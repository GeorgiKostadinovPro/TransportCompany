package org.example.data.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.example.data.entity.enums.DriverType;
import org.example.data.common.BaseEntityModel;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee extends BaseEntityModel {
    @NotBlank(message = "Employee name cannot be blank!")
    @Size(max = 100, message = "Employee name can have up to 100 characters!")
    private String name;

    @Positive(message = "Salary must be positive!")
    private double salary;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Driver type cannot be null!")
    @Column(name = "driver_type")
    private DriverType driverType;

    @ManyToOne
    // optional. Hibernate will create a default company_id.
    // explicit implementation for customizable column name and constraints.
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Cargo> cargos = new HashSet<>();

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public DriverType getDriverType() { return driverType; }
    public void setDriverType(DriverType driverType) { this.driverType = driverType; }

    public Company getCompany() { return company; }
    public void setCompany(Company company) { this.company = company; }

    public Set<Cargo> getCargos() { return cargos; }
    public void setCargos(Set<Cargo> cargos) { this.cargos = cargos; }
}
