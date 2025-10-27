package org.example.core;

import org.example.core.contracts.ICargoService;
import org.example.data.configuration.SessionFactoryUtil;
import org.example.data.entity.*;
import org.example.dto.cargo.CreateCargoDto;
import org.example.util.DtoValidator;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDateTime;

public class CargoService implements ICargoService {
    public CargoService() {}

    @Override
    public void create(CreateCargoDto dto) {
        DtoValidator.validate(dto);

        try(Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Vehicle vehicle = session.get(Vehicle.class, dto.getVehicleId());
            Employee driver = session.get(Employee.class, dto.getDriverId());
            Client client = session.get(Client.class, dto.getClientId());
            Company company = session.get(Company.class, dto.getCompanyId());

            if(vehicle == null || driver == null || client == null || company == null) {
                throw new IllegalArgumentException("Related entity not found!");
            }

            Cargo cargo = new Cargo();
            cargo.setPrice(dto.getPrice());
            cargo.setIsPaid(dto.isPaid());
            cargo.setWeight(dto.getWeight());
            cargo.setOrigin(dto.getOrigin());
            cargo.setDestination(dto.getDestination());
            cargo.setDepartureDate(dto.getDepartureDate());
            cargo.setArrivalDate(dto.getArrivalDate());
            cargo.setType(dto.getType());
            cargo.setVehicle(vehicle);
            cargo.setDriver(driver);
            cargo.setClient(client);
            cargo.setCompany(company);
            cargo.setCreationDate(LocalDateTime.now());
            cargo.setModifiedDate(LocalDateTime.now());

            session.save(cargo);
            tx.commit();
        }
    }
}
