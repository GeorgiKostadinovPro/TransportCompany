package org.example.dto.employee;

public class DriverWithTotalRevenueDto {
    private String driverName;
    private double totalRevenue;

    public DriverWithTotalRevenueDto(String driverName, double totalRevenue) {
        this.driverName = driverName;
        this.totalRevenue = totalRevenue;
    }

    public String getDriverName() { return driverName; }
    public double getTotalRevenue() { return totalRevenue; }
}
