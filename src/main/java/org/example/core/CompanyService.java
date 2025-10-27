package org.example.core;

import org.example.core.contracts.ICompanyService;
import org.example.data.configuration.SessionFactoryUtil;
import org.example.data.entity.Company;
import org.example.dto.company.CreateCompanyDto;
import org.example.dto.company.UpdateCompanyDto;
import org.example.util.DtoValidator;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.validation.*;
import java.time.LocalDateTime;
import java.util.List;

public class CompanyService implements ICompanyService {
    public CompanyService() {}

    @Override
    public void create(CreateCompanyDto dto) {
        DtoValidator.validate(dto);

        Company company = new Company();
        company.setName(dto.getName());
        company.setAddress(dto.getAddress());
        company.setRevenue(dto.getRevenue());
        company.setCreationDate(LocalDateTime.now());
        company.setModifiedDate(LocalDateTime.now());

        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(company);
            tx.commit();
        }
    }

    @Override
    public void update(UpdateCompanyDto dto) {
        DtoValidator.validate(dto);

        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Company company = session.get(Company.class, dto.getId());
            if (company == null) {
                throw new IllegalArgumentException("Company with id " + dto.getId() + " not found.");
            }

            company.setName(dto.getName());
            company.setAddress(dto.getAddress());
            company.setRevenue(dto.getRevenue());
            company.setModifiedDate(LocalDateTime.now());

            session.update(company);
            tx.commit();
        }
    }

    @Override
    public void delete(long id) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Company company = session.get(Company.class, id);
            if (company == null) {
                throw new IllegalArgumentException("Company with id " + id + " not found.");
            }

            session.delete(company);
            tx.commit();
        }
    }

    @Override
    public List<Company> getSortedByNameAndRevenue() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM Company c ORDER BY c.name ASC, c.revenue DESC", Company.class
            ).list();
        }
    }
}
