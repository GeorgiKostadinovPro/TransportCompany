package org.example;

import org.example.core.CompanyService;
import org.example.core.contracts.ICompanyService;
import org.example.data.configuration.SessionFactoryUtil;
import org.example.dto.CreateCompanyDto;
import org.example.dto.UpdateCompanyDto;
import org.hibernate.Session;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            ICompanyService companyService = new CompanyService();

            CreateCompanyDto createDto = new CreateCompanyDto(
                    "FastTransport",
                    "123 Main Street",
                    7500.00
            );

            companyService.createCompany(createDto);
            System.out.println("Company created successfully!");

            // --- UPDATE ---
            UpdateCompanyDto updateDto = new UpdateCompanyDto(
                    1, // assuming the company ID is 1
                    "FastTransport Ltd",
                    "123 Main Street, Suite 5",
                    8000.00
            );

            companyService.updateCompany(updateDto);
            System.out.println("Company updated successfully!");

            // --- DELETE ---
            // Uncomment if you want to delete
            // companyService.deleteCompany(1);
            // System.out.println("Company deleted successfully!");
        }
    }
}
