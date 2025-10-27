package org.example.core;

import org.example.core.contracts.IEmployeeService;
import org.example.data.configuration.SessionFactoryUtil;
import org.example.data.entity.Company;
import org.example.data.entity.Employee;
import org.example.dto.employee.CreateEmployeeDto;
import org.example.dto.employee.UpdateEmployeeDto;
import org.example.util.DtoValidator;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public class EmployeeService implements IEmployeeService {
    public EmployeeService() {}

    @Override
    public void create(CreateEmployeeDto dto) {
        DtoValidator.validate(dto);

        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Company company = session.get(Company.class, dto.getCompanyId());
            if (company == null) {
                throw new IllegalArgumentException("Company not found with id: " + dto.getCompanyId());
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

    @Override
    public void update(UpdateEmployeeDto dto) {
        DtoValidator.validate(dto);

        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Employee employee = session.get(Employee.class, dto.getId());
            if (employee == null) {
                throw new IllegalArgumentException("Employee not found with id: " + dto.getId());
            }

            employee.setName(dto.getName());
            employee.setSalary(dto.getSalary());
            employee.setDriverType(dto.getDriverType());
            employee.setModifiedDate(LocalDateTime.now());

            session.update(employee);
            tx.commit();
        }
    }

    @Override
    public void delete(long id) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Employee employee = session.get(Employee.class, id);
            if (employee == null) {
                throw new IllegalArgumentException("Employee not found with id: " + id);
            }

            session.delete(employee);
            tx.commit();
        }
    }

    @Override
    public List<Employee> getByCompanyIdAndSortByQualificationAndSalary(long companyId) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            List<Employee> employees = session.createQuery(
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
}
