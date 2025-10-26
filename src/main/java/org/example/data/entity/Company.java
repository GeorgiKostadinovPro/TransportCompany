package org.example.data.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.example.data.common.BaseEntityModel;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "companies")
public class Company extends BaseEntityModel {
    @NotBlank(message = "Company name cannot be blank!")
    @Size(max = 100, message = "Company name must be at most 100 characters!")
    @Pattern(regexp = "^([A-Z]).*", message = "Company name must start with a capital letter!")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Address cannot be blank!")
    @Size(max = 100, message = "Address must be at most 100 characters!")
    private String address;

    @Positive(message = "Revenue must be positive!")
    @Digits(integer = 10, fraction = 2, message = "Revenue must have max 10 digits and 2 decimals")
    private double revenue;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    // initialize to avoid NullPointerException => Follow EF Core good practices
    private Set<Employee> employees = new HashSet<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Vehicle> vehicles = new HashSet<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Client> clients = new HashSet<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Cargo> cargos = new HashSet<>();

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public double getRevenue() { return revenue; }
    public void setRevenue(double revenue) { this.revenue = revenue; }

    public Set<Employee> getEmployees() { return employees; }
    public void setEmployees(Set<Employee> employees) { this.employees = employees; }

    public Set<Employee> getVehicles() { return employees; }
    public void setVehicles(Set<Vehicle> vehicles) { this.vehicles = vehicles; }

    public Set<Client> getClients() { return clients; }
    public void setClients(Set<Client> clients) { this.clients = clients; }

    public Set<Cargo> getCargos() { return cargos; }
    public void setCargos(Set<Cargo> cargos) { this.cargos = cargos; }
}
