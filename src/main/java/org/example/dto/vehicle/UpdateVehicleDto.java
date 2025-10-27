package org.example.dto.vehicle;

import org.example.data.entity.enums.VehicleType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class UpdateVehicleDto {
    @Positive(message = "ID must be a positive number!")
    private long id;

    @NotBlank(message = "Registration number cannot be blank!")
    @Size(max = 50, message = "Registration number can have up to 50 characters!")
    private String registrationNumber;

    private VehicleType type;

    public UpdateVehicleDto(long id, String registrationNumber, VehicleType type) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.type = type;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

    public VehicleType getType() { return type; }
    public  void setType(VehicleType type) { this.type = type; }
}
