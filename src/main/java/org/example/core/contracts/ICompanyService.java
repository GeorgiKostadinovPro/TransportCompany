package org.example.core.contracts;

import org.example.data.entity.Company;
import org.example.dto.company.CreateCompanyDto;
import org.example.dto.company.UpdateCompanyDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ICompanyService {
    void create(CreateCompanyDto dto);
    void update(UpdateCompanyDto dto);
    void delete(long id);

    List<Company> getSortedByNameAndRevenue();
    double getCompanyRevenueForPeriod(long companyId, LocalDateTime startDate, LocalDateTime endDate);
}
