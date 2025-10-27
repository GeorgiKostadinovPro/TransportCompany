package org.example.dto.cargo;

import org.example.data.entity.enums.CargoType;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class CreateCargoDto {
    @Positive(message = "Price must be positive!")
    private double price;

    @Column(name = "is_paid")
    private boolean isPaid;

    @Positive(message = "Weight must be positive!")
    private double weight;

    @NotBlank(message = "Origin cannot be blank!")
    @Size(max = 100)
    private String origin;

    @NotBlank(message = "Destination cannot be blank!")
    @Size(max = 100)
    private String destination;

    @NotNull(message = "Departure date cannot be blank!")
    @FutureOrPresent(message = "Departure date cannot be in the past!")
    private LocalDateTime departureDate;

    @NotNull(message = "Arrival date cannot be blank!")
    @Future(message = "Arrival date must be in the future!")
    private LocalDateTime arrivalDate;

    @NotNull(message = "Vehicle type cannot be null!")
    private CargoType type;

    @Positive(message = "Vehicle ID must be positive!")
    private long vehicleId;

    @Positive(message = "Driver ID must be positive!")
    private long driverId;

    @Positive(message = "Client ID must be positive!")
    private long clientId;

    @Positive(message = "Company ID must be positive!")
    private long companyId;

    public CreateCargoDto(double price, boolean isPaid, double weight, String origin, String destination,
                          LocalDateTime departureDate, LocalDateTime arrivalDate, CargoType type,
                          long vehicleId, long driverId, long clientId, long companyId) {
        this.price = price;
        this.isPaid = isPaid;
        this.weight = weight;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.type = type;
        this.vehicleId = vehicleId;
        this.driverId = driverId;
        this.clientId = clientId;
        this.companyId = companyId;
    }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public boolean isPaid() { return isPaid; }
    public void setPaid(boolean paid) { this.isPaid = paid; }

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

    public long getVehicleId() { return vehicleId; }
    public void setVehicleId(long vehicleId) { this.vehicleId = vehicleId; }

    public long getDriverId() { return driverId; }
    public void setDriverId(long driverId) { this.driverId = driverId; }

    public long getClientId() { return clientId; }
    public void setClientId(long clientId) { this.clientId = clientId; }

    public long getCompanyId() { return companyId; }
    public void setCompanyId(long companyId) { this.companyId = companyId; }
}
