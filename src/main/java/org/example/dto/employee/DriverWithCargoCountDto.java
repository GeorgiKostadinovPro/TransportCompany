package org.example.dto.employee;

public class DriverWithCargoCountDto {
    private String driverName;
    private long cargoCount;

    public DriverWithCargoCountDto(String driverName, long cargoCount) {
        this.driverName = driverName;
        this.cargoCount = cargoCount;
    }

    public String getDriverName() { return driverName; }
    public long getCargoCount() { return cargoCount; }
}
