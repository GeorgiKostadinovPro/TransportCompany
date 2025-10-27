package org.example.core.contracts;

import org.example.dto.CreateCompanyDto;
import org.example.dto.UpdateCompanyDto;

public interface ICompanyService {
    void create(CreateCompanyDto dto);
    void update(UpdateCompanyDto dto);
    void delete(long id);
}
