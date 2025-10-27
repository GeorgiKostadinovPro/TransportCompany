package org.example.controller.contracts;

public interface ICompanyController {
    String create(String[] args);
    String update(String[] args);
    String delete(String[] args);

    String getCompaniesSortedByNameAndRevenue();
    String getCompanyRevenueForPeriod(String[] args);
}
