package org.example;

import org.example.core.ClientService;
import org.example.core.CompanyService;
import org.example.core.contracts.IClientService;
import org.example.core.contracts.ICompanyService;
import org.example.data.configuration.SessionFactoryUtil;
import org.example.dto.client.CreateClientDto;
import org.example.dto.client.UpdateClientDto;
import org.example.dto.company.CreateCompanyDto;
import org.example.dto.company.UpdateCompanyDto;
import org.hibernate.Session;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            ///  COMPANY
            ICompanyService companyService = new CompanyService();

            CreateCompanyDto createDto = new CreateCompanyDto(
                    "FastTransport",
                    "123 Main Street",
                    7500.00
            );
            companyService.create(createDto);
            System.out.println("Company created successfully!");

            UpdateCompanyDto updateDto = new UpdateCompanyDto(
                    1, // assuming the company ID is 1
                    "FastTransport Ltd",
                    "123 Main Street, Suite 5",
                    8000.00
            );
            companyService.update(updateDto);
            System.out.println("Company updated successfully!");

            ///  CLIENT
            IClientService clientService = new ClientService();
            CreateClientDto createClientDto = new CreateClientDto(
                    "Ivan Ivanov",
                    "ivan@mail.com",
                    "0888123456",
                    false,
                    1L);
            clientService.create(createClientDto);
            System.out.println("Client created successfully!");

            UpdateClientDto updateClientDto = new UpdateClientDto(
                    1L,
                    "Ivan Ivanov",
                    "ivan.updated@mail.com",
                    "0888999999",
                    true);
            clientService.update(updateClientDto);
            System.out.println("Client updated successfully!");
        }
    }
}
