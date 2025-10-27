package org.example.core;

import org.example.core.contracts.ICargoService;
import org.example.data.configuration.SessionFactoryUtil;
import org.example.data.entity.*;
import org.example.dto.cargo.CargoDto;
import org.example.dto.cargo.CreateCargoDto;
import org.example.util.DtoValidator;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDateTime;
import java.util.List;

import static org.example.common.ExceptionMessages.*;

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
                throw new IllegalArgumentException(RELATED_ENTITY_NOT_FOUND);
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

    public void payForCargo(long cargoId) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Cargo cargo = session.get(Cargo.class, cargoId);
            if (cargo == null) {
                throw new IllegalArgumentException(INVALID_ENTITY_ID);
            }

            cargo.setIsPaid(true);
            cargo.setModifiedDate(LocalDateTime.now());

            session.update(cargo);
            tx.commit();
        }
    }

    @Override
    public CargoDto getCargoById(long id) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Cargo cargo = session.get(Cargo.class, id);
            if (cargo == null) {
                throw new IllegalArgumentException(INVALID_ENTITY_ID);
            }

            tx.commit();

            return new CargoDto(
                    cargo.getId(),
                    cargo.getCreationDate(),
                    cargo.getPrice(),
                    cargo.getIsPaid(),
                    cargo.getWeight(),
                    cargo.getOrigin(),
                    cargo.getDestination(),
                    cargo.getDepartureDate(),
                    cargo.getArrivalDate(),
                    cargo.getType(),
                    cargo.getVehicle().getRegistrationNumber(),
                    cargo.getDriver().getName(),
                    cargo.getClient().getName(),
                    cargo.getCompany().getName()
            );
        }
    }

    @Override
    public List<Cargo> getByCompanyIdAndSortByDestination(long companyId) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            List<Cargo> cargos = session
                    .createQuery("FROM Cargo c WHERE c.company.id = :companyId ORDER BY c.destination ASC", Cargo.class)
                    .setParameter("companyId", companyId)
                    .getResultList();

            tx.commit();
            return cargos;
        }
    }

    @Override
    public long getCountByCompanyId(long companyId) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            long total = session
                    .createQuery("SELECT COUNT(c) FROM Cargo c WHERE c.company.id = :companyId", Long.class)
                    .setParameter("companyId", companyId)
                    .getSingleResult();

            tx.commit();
            return total;
        }
    }

    @Override
    public double getTotalRevenueByCompanyId(long companyId) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            double totalAmount = session
                    .createQuery("SELECT SUM(c.price) FROM Cargo c WHERE c.company.id = :companyId", Double.class)
                    .setParameter("companyId", companyId)
                    .getSingleResult();

            tx.commit();
            return totalAmount;
        }
    }
}
