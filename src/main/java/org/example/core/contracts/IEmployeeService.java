package org.example.core.contracts;

import org.example.data.entity.Employee;
import org.example.dto.employee.CreateEmployeeDto;
import org.example.dto.employee.DriverWithCargoCountDto;
import org.example.dto.employee.DriverWithTotalRevenueDto;
import org.example.dto.employee.UpdateEmployeeDto;

import java.util.List;

public interface IEmployeeService {
    void create(CreateEmployeeDto dto);
    void update(UpdateEmployeeDto dto);
    void delete(long id);

    List<Employee> getByCompanyIdAndSortByQualificationAndSalary(long companyId);
    List<DriverWithCargoCountDto> getDriversWithCargoCountByCompanyId(long companyId);
    List<DriverWithTotalRevenueDto> getDriverRevenueByCompanyId(long companyId);
}
