package org.example.core.contracts;

import org.example.dto.employee.CreateEmployeeDto;
import org.example.dto.employee.UpdateEmployeeDto;

public interface IEmployeeService {
    void create(CreateEmployeeDto dto);
    void update(UpdateEmployeeDto dto);
    void delete(long id);
}
