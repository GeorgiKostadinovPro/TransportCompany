package org.example.dto.client;

import javax.validation.constraints.*;

public class UpdateClientDto {
    private long id;

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

    public UpdateClientDto(long id, String name, String email, String phone, boolean hasPaid) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.hasPaid = hasPaid;
    }

    // Getters and setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public boolean isHasPaid() { return hasPaid; }
    public void setHasPaid(boolean hasPaid) { this.hasPaid = hasPaid; }
}
