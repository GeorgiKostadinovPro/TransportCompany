package org.example.core.contracts;

import org.example.dto.company.CreateCompanyDto;
import org.example.dto.company.UpdateCompanyDto;

public interface ICompanyService {
    void create(CreateCompanyDto dto);
    void update(UpdateCompanyDto dto);
    void delete(long id);
}
