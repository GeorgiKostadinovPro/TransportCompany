package org.example.core.contracts;

import org.example.data.entity.Company;
import org.example.dto.company.CreateCompanyDto;
import org.example.dto.company.UpdateCompanyDto;

import java.util.List;

public interface ICompanyService {
    void create(CreateCompanyDto dto);
    void update(UpdateCompanyDto dto);
    void delete(long id);

    List<Company> getSortedByNameAndRevenue();
}
