package org.example.core.contracts;

import org.example.data.entity.Cargo;
import org.example.dto.cargo.CargoDto;
import org.example.dto.cargo.CreateCargoDto;

import java.util.List;

public interface ICargoService {
    void create(CreateCargoDto dto);
    void payForCargo(long id);

    CargoDto getCargoById(long id);
    List<Cargo> getByCompanyIdAndSortByDestination(long companyId);
}
