package org.example.data.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.example.data.common.BaseEntityModel;
import org.example.data.entity.enums.CargoType;

import java.time.LocalDateTime;

@Entity
@Table(name = "cargos")
public class Cargo extends BaseEntityModel {
    @Positive(message = "Price must be positive!")
    private double price;
    
    @Column(name = "is_paid")
    private boolean isPaid;

    @Positive(message = "Weight must be positive!")
    private double weight;

    @NotBlank(message = "Origin cannot be blank!")
    @Size(max = 100, message = "Origin can have up to 100 characters!")
    private String origin;

    @NotBlank(message = "Destination cannot be blank!")
    @Size(max = 100, message = "Destination can have up to 100 characters!")
    private String destination;

    @Column(name = "departure_date", nullable = false)
    @FutureOrPresent(message = "Departure date cannot be in the past!")
    private LocalDateTime departureDate;

    @Column(name = "arrival_date", nullable = false)
    @Future(message = "Arrival date must be in the future!")
    private LocalDateTime arrivalDate;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Cargo type cannot be null!")
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

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public boolean getIsPaid() { return isPaid; }
    public void setIsPaid(boolean isPaid) { this.isPaid = isPaid; }

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

    public Company getCompany() { return company; }
    public void setCompany(Company company) { this.company = company; }
}
