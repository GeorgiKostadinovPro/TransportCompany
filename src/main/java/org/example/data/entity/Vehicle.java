package org.example.data.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.example.data.common.BaseEntityModel;
import org.example.data.enums.VehicleType;

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
    private TransportCompany company;

    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

    public VehicleType getType() { return type; }
    public void setType(VehicleType type) { this.type = type; }

    public double getCapacity() { return capacity; }
    public void setCapacity(double capacity) { this.capacity = capacity; }

    public TransportCompany getCompany() { return company; }
    public void setCompany(TransportCompany company) { this.company = company; }
}
