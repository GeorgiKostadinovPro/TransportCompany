package org.example.core.contracts;

import org.example.dto.CreateCompanyDto;
import org.example.dto.UpdateCompanyDto;

public interface ICompanyService {
    void createCompany(CreateCompanyDto dto);
    void updateCompany(UpdateCompanyDto dto);
    void deleteCompany(long id);
}
