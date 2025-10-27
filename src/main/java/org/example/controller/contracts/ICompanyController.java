package org.example.controller.contracts;

public interface ICompanyController {
    String create(String[] args);
    String update(String[] args);
    String delete(String[] args);

    String getSortedByNameAndRevenue();
    String getCompanyRevenueForPeriod(String[] args);
}
