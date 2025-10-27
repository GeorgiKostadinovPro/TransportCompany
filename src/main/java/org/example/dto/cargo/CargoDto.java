package org.example.dto.cargo;

import org.example.data.entity.enums.CargoType;
import org.example.util.DateFormatter;

import java.time.LocalDateTime;

///  No Validation - only for extracting data from DB.
///  If Cargo was saved in DB then it passed validation.
public class CargoDto {
    private long id;

    private LocalDateTime creationDate;

    private double price;

    private boolean isPaid;

    private double weight;

    private String origin;

    private String destination;

    private LocalDateTime departureDate;

    private LocalDateTime arrivalDate;

    private CargoType type;

    private String vehicle;

    private String driver;

    private String client;

    private String company;

    public CargoDto(long id, LocalDateTime creationDate, double price, boolean isPaid, double weight, String origin, String destination,
                          LocalDateTime departureDate, LocalDateTime arrivalDate, CargoType type,
                          String vehicle, String driver, String client, String company) {
        this.id = id;
        this.creationDate = creationDate;
        this.price = price;
        this.isPaid = isPaid;
        this.weight = weight;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.type = type;
        this.vehicle = vehicle;
        this.driver = driver;
        this.client = client;
        this.company = company;
    }

    public long getId() { return id; }
    public LocalDateTime getCreationDate() { return creationDate; }
    public double getPrice() { return price; }
    public boolean getIsPaid() { return isPaid; }
    public double getWeight() { return weight; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public LocalDateTime getDepartureDate() { return departureDate; }
    public LocalDateTime getArrivalDate() { return arrivalDate; }
    public CargoType getType() { return type; }
    public String getVehicle() { return vehicle; }
    public String getDriver() { return driver; }
    public String getClient() { return client; }
    public String getCompany() { return company; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("---------------  BASE INFO ---------------\n");
        sb.append("Cargo: #").append(this.getId()).append("\n");
        sb.append("Company : ").append(this.getCompany()).append("\n");
        sb.append("Driver : ").append(this.getDriver()).append("\n");
        sb.append("Vehicle : ").append(this.getVehicle()).append("\n");
        sb.append("Client : ").append(this.getClient()).append("\n");

        sb.append("---------------  GENERAL INFO  ---------------\n");
        sb.append("Creation Date: ").append(DateFormatter.formatLocalDateTime(this.getCreationDate())).append("\n");
        sb.append("Price: $").append(this.getPrice()).append("\n");
        sb.append("Paid: ").append(this.getIsPaid()).append("\n");
        sb.append("Weight: ").append(this.getWeight()).append("\n");
        sb.append("Origin: ").append(this.getOrigin()).append("\n");
        sb.append("Destination: ").append(this.getDestination()).append("\n");
        sb.append("Departure Date: ").append(DateFormatter.formatLocalDateTime(getDepartureDate())).append("\n");
        sb.append("Arrival Date: ").append(DateFormatter.formatLocalDateTime(getArrivalDate())).append("\n");
        sb.append("Type: ").append(this.getType()).append("\n");

        return sb.toString();
    }
}
