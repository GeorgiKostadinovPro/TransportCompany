package org.example.controller;


import org.example.controller.contracts.ICompanyController;
import org.example.core.contracts.ICompanyService;
import org.example.dto.company.CreateCompanyDto;
import org.example.dto.company.UpdateCompanyDto;

import java.time.LocalDateTime;
import java.util.StringJoiner;

import static org.example.common.OutputMessages.*;

public class CompanyController implements ICompanyController {
    private final ICompanyService companyService;

    public CompanyController(ICompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public String create(String[] args) {
        String name = args[0];
        String addr = args[1];
        double revenue = Double.parseDouble(args[2]);

        companyService.create(new CreateCompanyDto(name, addr, revenue));

        return COMPANY_CREATED_SUCCESSFULLY;
    }

    @Override
    public String update(String[] args) {
        long id = Long.parseLong(args[0]);
        String name = args[1];
        String addr = args[2];
        double revenue = Double.parseDouble(args[3]);

        companyService.update(new UpdateCompanyDto(id, name, addr, revenue));

        return COMPANY_UPDATED_SUCCESSFULLY;
    }

    @Override
    public String delete(String[] args) {
        long id = Long.parseLong(args[0]);

        companyService.delete(id);

        return COMPANY_DELETED_SUCCESSFULLY;
    }

    @Override
    public String getCompaniesSortedByNameAndRevenue() {
        var companies = companyService.getSortedByNameAndRevenue();

        StringJoiner res = new StringJoiner(System.lineSeparator());
        companies.forEach(c ->
                res.add(String.format(COMPANY_NAME_AND_REVENUE, c.getName(), c.getRevenue()))
        );

        return res.toString();
    }

    @Override
    public String getCompanyRevenueForPeriod(String[] args) {
        long companyId = Long.parseLong(args[0]);
        LocalDateTime start = LocalDateTime.parse(args[1]);
        LocalDateTime end = LocalDateTime.parse(args[2]);

        double revenue = companyService.getCompanyRevenueForPeriod(companyId, start, end);
        return String.format(COMPANY_REVENUE_FOR_PERIOD, companyId, revenue);
    }
}
