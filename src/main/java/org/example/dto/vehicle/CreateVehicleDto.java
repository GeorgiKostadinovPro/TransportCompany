package org.example.dto.vehicle;

import org.example.data.entity.enums.VehicleType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class CreateVehicleDto {
    @NotBlank(message = "Registration number cannot be blank!")
    @Size(max = 50, message = "Registration number can have up to 50 characters!")
    private String registrationNumber;

    @NotNull(message = "Vehicle type cannot be null!")
    private VehicleType type;

    @Positive(message = "Company ID must be a positive number!")
    private long companyId;

    public CreateVehicleDto(String registrationNumber, VehicleType type, long companyId) {
        this.registrationNumber = registrationNumber;
        this.type = type;
        this.companyId = companyId;
    }

    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }

    public VehicleType getType() { return type; }
    public  void setType(VehicleType type) { this.type = type; }

    public long getCompanyId() { return companyId; }
    public void setCompanyId(long companyId) { this.companyId = companyId; }
}
