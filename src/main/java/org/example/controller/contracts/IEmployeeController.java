package org.example.controller.contracts;

public interface IEmployeeController {
    String create(String[] args);
    String update(String[] args);
    String delete(String[] args);

    String getByCompanyIdAndSortByQualificationAndSalary(String[] args);
    String getDriversWithCargoCountByCompanyId(String[] args);
    String getDriverRevenueByCompanyId(String[] args);
}
