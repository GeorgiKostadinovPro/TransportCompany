package org.example.dto.employee;

public class DriverWithTotalRevenueDto {
    private String driverName;
    private long totalRevenue;

    public DriverWithTotalRevenueDto(String driverName, long totalRevenue) {
        this.driverName = driverName;
        this.totalRevenue = totalRevenue;
    }

    public String getDriverName() { return driverName; }
    public long getCargoCount() { return totalRevenue; }
}
