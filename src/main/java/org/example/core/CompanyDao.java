package org.example.core;

import org.example.data.configuration.SessionFactoryUtil;
import org.example.data.entity.Company;
import org.example.dto.company.CreateCompanyDto;
import org.example.dto.company.UpdateCompanyDto;
import org.example.util.DtoValidator;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDateTime;
import java.util.List;

import static org.example.common.ExceptionMessages.INVALID_ENTITY_ID;

public class CompanyDao {
    public static void create(CreateCompanyDto dto) {
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

    public static void update(UpdateCompanyDto dto) {
        DtoValidator.validate(dto);

        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Company company = session.get(Company.class, dto.getId());
            if (company == null) {
                throw new IllegalArgumentException(INVALID_ENTITY_ID);
            }

            company.setName(dto.getName());
            company.setAddress(dto.getAddress());
            company.setRevenue(dto.getRevenue());
            company.setModifiedDate(LocalDateTime.now());

            session.update(company);
            tx.commit();
        }
    }

    public static void delete(long id) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Company company = session.get(Company.class, id);
            if (company == null) {
                throw new IllegalArgumentException(INVALID_ENTITY_ID);
            }

            session.delete(company);
            tx.commit();
        }
    }

    public static List<Company> getSortedByNameAndRevenue() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            List<Company> companies = session
                    .createQuery("FROM Company c ORDER BY c.name ASC, c.revenue DESC", Company.class)
                    .getResultList();

            tx.commit();
            return companies;
        }
    }

    public static double getCompanyRevenueForPeriod(long companyId, LocalDateTime startDate, LocalDateTime endDate) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            double revenue = session
                    .createQuery(
                    """
                        SELECT COALESCE(SUM(c.price), 0)
                        FROM Cargo c
                        WHERE c.company.id = :companyId
                        AND c.departureDate BETWEEN :startDate AND :endDate
                       """, Double.class)
                    .setParameter("companyId", companyId)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getSingleResult();

            tx.commit();
            return revenue;
        }
    }
}
