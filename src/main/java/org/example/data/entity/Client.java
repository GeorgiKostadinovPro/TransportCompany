package org.example.data.entity;

import javax.persistence.*;
import org.example.data.common.BaseEntityModel;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "clients")
public class Client extends BaseEntityModel {
    @NotBlank
    @Size(max = 100)
    private String name;

    @Size(max = 100)
    private String email;

    @Size(max = 20)
    private String phone;

    private boolean hasPaid;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private TransportCompany company;

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public boolean isHasPaid() { return hasPaid; }
    public void setHasPaid(boolean hasPaid) { this.hasPaid = hasPaid; }

    public TransportCompany getCompany() { return company; }
    public void setCompany(TransportCompany company) { this.company = company; }
}
