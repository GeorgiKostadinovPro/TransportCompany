package org.example.core;

import org.example.core.contracts.IClientService;
import org.example.data.configuration.SessionFactoryUtil;
import org.example.data.entity.Client;
import org.example.data.entity.Company;
import org.example.dto.client.CreateClientDto;
import org.example.dto.client.UpdateClientDto;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.validation.*;

import javax.validation.ConstraintViolation;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientService implements IClientService {
    private final Validator validator;

    public ClientService() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    private <T> void validateDto(T dto) {
        Set<ConstraintViolation<T>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            String msg = violations.stream()
                    .map(v -> v.getPropertyPath() + " " + v.getMessage())
                    .collect(Collectors.joining("; "));
            throw new IllegalArgumentException("Validation failed: " + msg);
        }
    }

    @Override
    public void create(CreateClientDto dto) {
        validateDto(dto);

        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Company company = session.get(Company.class, dto.getCompanyId());
            if (company == null) {
                throw new IllegalArgumentException("Company not found with id: " + dto.getCompanyId());
            }

            Client client = new Client();
            client.setName(dto.getName());
            client.setEmail(dto.getEmail());
            client.setPhone(dto.getPhone());
            client.setHasPaid(dto.isHasPaid());
            client.setCompany(company);
            client.setCreationDate(LocalDateTime.now());
            client.setModifiedDate(LocalDateTime.now());

            session.save(client);
            tx.commit();
        }
    }

    @Override
    public void update(UpdateClientDto dto) {
        validateDto(dto);

        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Client client = session.get(Client.class, dto.getId());
            if (client == null) {
                throw new IllegalArgumentException("Client not found with id: " + dto.getId());
            }

            client.setName(dto.getName());
            client.setEmail(dto.getEmail());
            client.setPhone(dto.getPhone());
            client.setHasPaid(dto.isHasPaid());
            client.setModifiedDate(LocalDateTime.now());

            session.update(client);
            tx.commit();
        }
    }

    @Override
    public void delete(long id) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Client client = session.get(Client.class, id);
            if (client == null) {
                throw new IllegalArgumentException("Client not found with id: " + id);
            }

            session.delete(client);
            tx.commit();
        }
    }
}
