package org.example.dto.company;

import javax.validation.constraints.*;

public class CreateCompanyDto {
    @NotBlank(message = "Company name cannot be blank!")
    @Size(max = 100, message = "Company name must be at most 100 characters!")
    @Pattern(regexp = "^([A-Z]).*", message = "Company name must start with a capital letter!")
    private String name;

    @NotBlank(message = "Address cannot be blank!")
    @Size(max = 100, message = "Address must be at most 100 characters!")
    private String address;

    @Positive(message = "Revenue must be positive!")
    @Digits(integer = 10, fraction = 2, message = "Revenue must have max 10 digits and 2 decimals")
    private double revenue;

    public CreateCompanyDto(String name, String address, double revenue) {
        this.name = name;
        this.address = address;
        this.revenue = revenue;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public double getRevenue() { return revenue; }
    public void setRevenue(double revenue) { this.revenue = revenue; }
}
