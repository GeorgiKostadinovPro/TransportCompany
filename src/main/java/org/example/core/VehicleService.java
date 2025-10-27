package org.example.core;

import org.example.core.contracts.IVehicleService;
import org.example.data.configuration.SessionFactoryUtil;
import org.example.data.entity.Company;
import org.example.data.entity.Vehicle;
import org.example.dto.vehicle.CreateVehicleDto;
import org.example.dto.vehicle.UpdateVehicleDto;
import org.example.util.DtoValidator;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDateTime;

import static org.example.common.ExceptionMessages.INVALID_ENTITY_ID;

public class VehicleService implements IVehicleService {
    public VehicleService() {}

    @Override
    public void create(CreateVehicleDto dto) {
        DtoValidator.validate(dto);

        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Company company = session.get(Company.class, dto.getCompanyId());
            if (company == null) {
                throw new IllegalArgumentException(INVALID_ENTITY_ID);
            }

            Vehicle vehicle = new Vehicle();
            vehicle.setRegistrationNumber(dto.getRegistrationNumber());
            vehicle.setType(dto.getType());
            vehicle.setCompany(company);
            vehicle.setCreationDate(LocalDateTime.now());
            vehicle.setModifiedDate(LocalDateTime.now());

            session.save(vehicle);
            tx.commit();
        }
    }

    @Override
    public void update(UpdateVehicleDto dto) {
        DtoValidator.validate(dto);

        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Vehicle vehicle = session.get(Vehicle.class, dto.getId());
            if (vehicle == null) {
                throw new IllegalArgumentException(INVALID_ENTITY_ID);
            }

            vehicle.setRegistrationNumber(dto.getRegistrationNumber());
            vehicle.setType(dto.getType());
            vehicle.setModifiedDate(LocalDateTime.now());

            session.update(vehicle);
            tx.commit();
        }
    }

    @Override
    public void delete(long id) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Vehicle vehicle = session.get(Vehicle.class, id);
            if (vehicle == null) {
                throw new IllegalArgumentException(INVALID_ENTITY_ID);
            }

            session.delete(vehicle);
            tx.commit();
        }
    }
}
