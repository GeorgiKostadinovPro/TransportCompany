package org.example.controller;

import org.example.controller.contracts.ICargoController;
import org.example.core.CargoService;
import org.example.core.contracts.ICargoService;
import org.example.data.entity.Cargo;
import org.example.data.entity.enums.CargoType;
import org.example.dto.cargo.CargoDto;
import org.example.dto.cargo.CreateCargoDto;
import org.example.util.FileService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.StringJoiner;

import static org.example.common.OutputMessages.*;

public class CargoController implements ICargoController {
    private final FileService fileService;
    private final ICargoService cargoService;

    public CargoController() {
        this.fileService = new FileService();
        this.cargoService = new CargoService();
    }

    @Override
    public String create(String[] args) {
        double price = Double.parseDouble(args[0]);
        boolean isPaid = Boolean.parseBoolean(args[1]);
        double weight = Double.parseDouble(args[2]);
        String origin = args[3];
        String destination = args[4];
        LocalDateTime departureDate = LocalDateTime.parse(args[5]);
        LocalDateTime arrivalDate = LocalDateTime.parse(args[6]);
        CargoType type = CargoType.valueOf(args[7]);
        long vehicleId = Long.parseLong(args[8]);
        long driverId = Long.parseLong(args[9]);
        long clientId = Long.parseLong(args[10]);
        long companyId = Long.parseLong(args[11]);

        CreateCargoDto dto = new CreateCargoDto(
                price, isPaid, weight, origin, destination,
                departureDate, arrivalDate, type,
                vehicleId, driverId, clientId, companyId
        );

        this.cargoService.create(dto);

        return CARGO_CREATED_SUCCESSFULLY;
    }

    @Override
    public String payForCargo(String[] args) {
        long id = Long.parseLong(args[0]);

        this.cargoService.payForCargo(id);

        return CARGO_PAYMENT_COMPLETED;
    }

    @Override
    public String saveCargoToFile(String[] args) {
        long cargoId = Long.parseLong(args[0]);

        CargoDto cargo = this.cargoService.getById(cargoId);
        this.fileService.saveCargoAsFile(cargo);

        return String.format(CARGO_SAVING_BEGAN, cargoId);
    }

    @Override
    public String readCargoFromFile(String[] args) {
        long cargoId = Long.parseLong(args[0]);

        String content = this.fileService.readCargoFromFile(cargoId);

        return String.format(CARGO_CONTENT_FROM_FILE, cargoId, content);
    }

    @Override
    public String getById(String[] args) {
        long id = Long.parseLong(args[0]);

        CargoDto cargo = this.cargoService.getById(id);

        return cargo.toString();
    }

    @Override
    public String getByCompanyAndSortByDestination(String[] args) {
        long companyId = Long.parseLong(args[0]);

        List<Cargo> cargos = this.cargoService
                .getByCompanyIdAndSortByDestination(companyId);

        StringJoiner res = new StringJoiner(System.lineSeparator());
        cargos.forEach(c -> res.add(
                String.format(CARGO_WITH_DESTINATION, c.getId(), c.getDestination())
        ));

        return res.toString();
    }

    @Override
    public String getCountByCompany(String[] args) {
        long companyId = Long.parseLong(args[0]);

        long count = this.cargoService.getCountByCompanyId(companyId);

        return String.format(CARGO_COUNT_BY_COMPANY, companyId, count);
    }

    @Override
    public String getTotalRevenueByCompany(String[] args) {
        long companyId = Long.parseLong(args[0]);

        double totalRevenue = this.cargoService.getTotalRevenueByCompanyId(companyId);

        return String.format(TOTAL_CARGO_REVENUE_BY_COMPANY, companyId, totalRevenue);
    }
}
