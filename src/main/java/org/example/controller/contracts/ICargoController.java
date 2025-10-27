package org.example.controller.contracts;

public interface ICargoController {
    String create(String[] args);
    String payForCargo(String[] args);

    String getById(String[] args);
    String getByCompanyAndSortByDestination(String[] args);
    String getCountByCompany(String[] args);
    String getTotalRevenueByCompany(String[] args);
}
