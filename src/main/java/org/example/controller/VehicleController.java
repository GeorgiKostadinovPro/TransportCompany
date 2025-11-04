package org.example.controller;

import org.example.controller.contracts.IVehicleController;
import org.example.core.EmployeeDao;
import org.example.core.VehicleDao;
import org.example.data.entity.enums.VehicleType;
import org.example.dto.vehicle.CreateVehicleDto;
import org.example.dto.vehicle.UpdateVehicleDto;

import static org.example.common.OutputMessages.*;

public class VehicleController implements IVehicleController {
    public VehicleController() {}

    @Override
    public String create(String[] args) {
        String registrationNumber = args[0];
        VehicleType type = VehicleType.valueOf(args[1]);
        long companyId = Long.parseLong(args[2]);

        VehicleDao.create(new CreateVehicleDto(registrationNumber, type, companyId));

        return VEHICLE_CREATED_SUCCESSFULLY;
    }

    @Override
    public String update(String[] args) {
        long id = Long.parseLong(args[0]);
        String registrationNumber = args[1];
        VehicleType type = VehicleType.valueOf(args[2]);

        VehicleDao.update(new UpdateVehicleDto(id, registrationNumber, type));

        return VEHICLE_UPDATED_SUCCESSFULLY;
    }

    @Override
    public String delete(String[] args) {
        long id = Long.parseLong(args[0]);

        EmployeeDao.delete(id);

        return VEHICLE_DELETED_SUCCESSFULLY;
    }
}
