package org.example.data.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.example.data.common.BaseEntityModel;
import org.example.data.enums.VehicleType;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vehicles")
public class Vehicle extends BaseEntityModel {
    @NotBlank
    @Size(max = 50)
    private String registrationNumber;

    @Enumerated(EnumType.STRING)
    private VehicleType type;

    @Positive
    private double capacity;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Cargo> cargos = new HashSet<>();

    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

    public VehicleType getType() { return type; }
    public void setType(VehicleType type) { this.type = type; }

    public double getCapacity() { return capacity; }
    public void setCapacity(double capacity) { this.capacity = capacity; }

    public Company getCompany() { return company; }
    public void setCompany(Company company) { this.company = company; }

    public Set<Cargo> getCargos() { return cargos; }
    public void setCargos(Set<Cargo> cargos) { this.cargos = cargos; }
}
