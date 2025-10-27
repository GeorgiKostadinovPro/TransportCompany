package org.example.dto.client;

import javax.validation.constraints.*;

public class CreateClientDto {
    @NotBlank(message = "Client name cannot be blank!")
    @Size(max = 50, message = "Client name must be up to 50 characters!")
    @Pattern(regexp = "^[A-Z].*", message = "Client name must start with a capital letter!")
    private String name;

    @NotBlank(message = "Client email cannot be blank!")
    @Email(message = "Invalid email format!")
    @Size(max = 100, message = "Email must be up to 100 characters!")
    private String email;

    @Pattern(regexp = "^[0-9+\\-() ]*$", message = "Phone number can only contain digits, spaces, +, -, and parentheses!")
    @Size(max = 20, message = "Phone number must be up to 20 characters!")
    private String phone;

    private boolean hasPaid;

    @Positive(message = "Company ID must be a positive number!")
    private long companyId;

    public CreateClientDto(String name, String email, String phone, boolean hasPaid, long companyId) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.hasPaid = hasPaid;
        this.companyId = companyId;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public boolean isHasPaid() { return hasPaid; }
    public void setHasPaid(boolean hasPaid) { this.hasPaid = hasPaid; }

    public long getCompanyId() { return companyId; }
    public void setCompanyId(long companyId) { this.companyId = companyId; }
}
