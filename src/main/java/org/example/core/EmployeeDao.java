package org.example.core;

import org.example.data.configuration.SessionFactoryUtil;
import org.example.data.entity.Company;
import org.example.data.entity.Employee;
import org.example.dto.employee.CreateEmployeeDto;
import org.example.dto.employee.DriverWithCargoCountDto;
import org.example.dto.employee.DriverWithTotalRevenueDto;
import org.example.dto.employee.UpdateEmployeeDto;
import org.example.util.DtoValidator;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDateTime;
import java.util.List;

import static org.example.common.ExceptionMessages.INVALID_ENTITY_ID;

public class EmployeeDao {
    public static void create(CreateEmployeeDto dto) {
        DtoValidator.validate(dto);

        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Company company = session.get(Company.class, dto.getCompanyId());
            if (company == null) {
                throw new IllegalArgumentException(INVALID_ENTITY_ID);
            }

            Employee employee = new Employee();
            employee.setName(dto.getName());
            employee.setSalary(dto.getSalary());
            employee.setDriverType(dto.getDriverType());
            employee.setCompany(company);
            employee.setCreationDate(LocalDateTime.now());
            employee.setModifiedDate(LocalDateTime.now());

            session.save(employee);
            tx.commit();
        }
    }

    public static void update(UpdateEmployeeDto dto) {
        DtoValidator.validate(dto);

        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Employee employee = session.get(Employee.class, dto.getId());
            if (employee == null) {
                throw new IllegalArgumentException(INVALID_ENTITY_ID);
            }

            employee.setName(dto.getName());
            employee.setSalary(dto.getSalary());
            employee.setDriverType(dto.getDriverType());
            employee.setModifiedDate(LocalDateTime.now());

            session.update(employee);
            tx.commit();
        }
    }

    public static void delete(long id) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Employee employee = session.get(Employee.class, id);
            if (employee == null) {
                throw new IllegalArgumentException(INVALID_ENTITY_ID);
            }

            session.delete(employee);
            tx.commit();
        }
    }

    public static List<Employee> getByCompanyIdAndSortByQualificationAndSalary(long companyId) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            List<Employee> employees = session
                    .createQuery(
                            "FROM Employee e " +
                                    "WHERE e.company.id = :companyId " +
                                    "ORDER BY e.driverType ASC, e.salary DESC", Employee.class
                    )
                    .setParameter("companyId", companyId)
                    .getResultList();

            tx.commit();
            return employees;
        }
    }

    public static List<DriverWithCargoCountDto> getDriversWithCargoCountByCompanyId(long companyId) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            List<DriverWithCargoCountDto> drivers = session
                    .createQuery(
                    """
                            SELECT new DriverWithCargoCountDto(e.name, COUNT(c.id))
                            FROM Employee e
                            JOIN Cargo c ON c.driver.id = e.id
                            WHERE e.company.id = :companyId
                            GROUP BY e.id
                            ORDER BY COUNT(c.id) DESC
                    """, DriverWithCargoCountDto.class)
                    .setParameter("companyId", companyId)
                    .getResultList();

            tx.commit();
            return drivers;
        }
    }

    public static List<DriverWithTotalRevenueDto> getDriverRevenueByCompanyId(long companyId) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            List<DriverWithTotalRevenueDto> driverRevenues = session
                    .createQuery(
                        """
                                SELECT new DriverWithTotalRevenueDto(e.name, COALESCE(SUM(c.price), 0))
                                FROM Employee e
                                JOIN Cargo c ON c.driver.id = e.id
                                WHERE e.company.id = :companyId
                                GROUP BY e.name
                                ORDER BY SUM(c.price) DESC
                            """, DriverWithTotalRevenueDto.class)
                    .setParameter("companyId", companyId)
                    .getResultList();

            tx.commit();
            return driverRevenues;
        }
    }
}
