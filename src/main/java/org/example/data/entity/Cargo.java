package org.example.data.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.example.data.common.BaseEntityModel;
import org.example.data.enums.CargoType;

import java.time.LocalDateTime;

@Entity
@Table(name = "cargos")
public class Cargo extends BaseEntityModel {
    @Positive
    private double weight;

    @NotBlank
    private String origin;

    @NotBlank
    private String destination;

    @Column(name = "departure_date")
    private LocalDateTime departureDate;

    @Column(name = "arrival_date")
    private LocalDateTime arrivalDate;

    @Enumerated(EnumType.STRING)
    private CargoType type;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee driver;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public LocalDateTime getDepartureDate() { return departureDate; }
    public void setDepartureDate(LocalDateTime departureDate) { this.departureDate = departureDate; }

    public LocalDateTime getArrivalDate() { return arrivalDate; }
    public void setArrivalDate(LocalDateTime arrivalDate) { this.arrivalDate = arrivalDate; }

    public CargoType getType() { return type; }
    public void setType(CargoType type) { this.type = type; }

    public Vehicle getVehicle() { return vehicle; }
    public void setVehicle(Vehicle vehicle) { this.vehicle = vehicle; }

    public Employee getDriver() { return driver; }
    public void setDriver(Employee driver) { this.driver = driver; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
}
