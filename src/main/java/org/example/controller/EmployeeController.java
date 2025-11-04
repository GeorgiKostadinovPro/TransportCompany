package org.example.controller;

import org.example.controller.contracts.IEmployeeController;
import org.example.core.EmployeeDao;
import org.example.data.entity.Employee;
import org.example.data.entity.enums.DriverType;
import org.example.dto.employee.CreateEmployeeDto;
import org.example.dto.employee.DriverWithCargoCountDto;
import org.example.dto.employee.DriverWithTotalRevenueDto;
import org.example.dto.employee.UpdateEmployeeDto;

import java.util.List;
import java.util.StringJoiner;

import static org.example.common.OutputMessages.*;

public class EmployeeController implements IEmployeeController {
    public EmployeeController() {}

    @Override
    public String create(String[] args) {
        String name = args[0];
        double salary = Double.parseDouble(args[1]);
        DriverType driverType = DriverType.valueOf(args[2]);
        long companyId = Long.parseLong(args[3]);

        EmployeeDao.create(new CreateEmployeeDto(name, salary, driverType, companyId));

        return EMPLOYEE_CREATED_SUCCESSFULLY;
    }

    @Override
    public String update(String[] args) {
        long id = Long.parseLong(args[0]);
        String name = args[1];
        double salary = Double.parseDouble(args[2]);
        DriverType driverType = DriverType.valueOf(args[3]);

        EmployeeDao.update(new UpdateEmployeeDto(id, name, salary, driverType));

        return EMPLOYEE_UPDATED_SUCCESSFULLY;
    }

    @Override
    public String delete(String[] args) {
        long id = Long.parseLong(args[0]);

        EmployeeDao.delete(id);

        return EMPLOYEE_DELETED_SUCCESSFULLY;
    }

    @Override
    public String getByCompanyIdAndSortByQualificationAndSalary(String[] args) {
        long companyId = Long.parseLong(args[0]);
        List<Employee> employees = EmployeeDao
                .getByCompanyIdAndSortByQualificationAndSalary(companyId);

        StringJoiner res = new StringJoiner(System.lineSeparator());
        employees.forEach(e -> res.add(
                String.format(EMPLOYEE_WITH_NAME_TYPE_PRICE,
                e.getName(),
                e.getDriverType(),
                e.getSalary())));

        return res.toString();
    }

    @Override
    public String getDriversWithCargoCountByCompanyId(String[] args) {
        long companyId = Long.parseLong(args[0]);
        List<DriverWithCargoCountDto> drivers = EmployeeDao
                .getDriversWithCargoCountByCompanyId(companyId);

        StringJoiner res = new StringJoiner(System.lineSeparator());
        drivers.forEach(d -> res.add(
                String.format(EMPLOYEE_WITH_CARGO_COUNT,
                d.getDriverName(),
                d.getCargoCount())));

        return res.toString();
    }

    @Override
    public String getDriverRevenueByCompanyId(String[] args) {
        long companyId = Long.parseLong(args[0]);
        List<DriverWithTotalRevenueDto> revenues = EmployeeDao
                .getDriverRevenueByCompanyId(companyId);

        StringJoiner res = new StringJoiner(System.lineSeparator());
        revenues.forEach(d -> res.add(
                String.format(EMPLOYEE_WITH_TOTAL_REVENUE,
                        d.getDriverName(),
                        d.getTotalRevenue())));

        return res.toString();
    }
}
