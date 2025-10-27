package org.example.core;

import org.example.core.contracts.ICompanyService;
import org.example.data.configuration.SessionFactoryUtil;
import org.example.data.entity.Company;
import org.example.dto.CreateCompanyDto;
import org.example.dto.UpdateCompanyDto;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.validation.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class CompanyService implements ICompanyService {
    private final Validator validator;

    public CompanyService() {
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
    public void create(CreateCompanyDto dto) {
        validateDto(dto);

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
        validateDto(dto);

        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Company company = session.get(Company.class, dto.getId());
            if (company == null) {
                tx.rollback();
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
                tx.rollback();
                throw new IllegalArgumentException("Company with id " + id + " not found.");
            }

            session.delete(company);
            tx.commit();
        }
    }
}
