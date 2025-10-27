package org.example.core;

import org.example.core.contracts.IClientService;
import org.example.data.configuration.SessionFactoryUtil;
import org.example.data.entity.Cargo;
import org.example.data.entity.Client;
import org.example.data.entity.Company;
import org.example.dto.client.CreateClientDto;
import org.example.dto.client.UpdateClientDto;
import org.example.util.DtoValidator;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.validation.*;
import java.time.LocalDateTime;

public class ClientService implements IClientService {
    public ClientService() {}

    @Override
    public void create(CreateClientDto dto) {
        DtoValidator.validate(dto);

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
        DtoValidator.validate(dto);

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

    public boolean hasClientPaidAllDebts(long clientId) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Client client = session.get(Client.class, clientId);
            if (client == null) {
                throw new IllegalArgumentException("Client not found!");
            }

            return client.getCargos().stream().allMatch(Cargo::getIsPaid);
        }
    }
}
