package org.example.core.contracts;

import org.example.dto.vehicle.CreateVehicleDto;
import org.example.dto.vehicle.UpdateVehicleDto;

public interface IVehicleService {
    void create(CreateVehicleDto dto);
    void update(UpdateVehicleDto dto);
    void delete(long id);
}
