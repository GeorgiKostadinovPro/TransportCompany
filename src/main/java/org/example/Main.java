package org.example;

import org.example.core.*;
import org.example.core.contracts.*;
import org.example.data.configuration.SessionFactoryUtil;
import org.example.data.entity.Cargo;
import org.example.data.entity.Company;
import org.example.data.entity.Employee;
import org.example.data.entity.enums.CargoType;
import org.example.data.entity.enums.DriverType;
import org.example.data.entity.enums.VehicleType;
import org.example.dto.cargo.CargoDto;
import org.example.dto.cargo.CreateCargoDto;
import org.example.dto.client.CreateClientDto;
import org.example.dto.client.UpdateClientDto;
import org.example.dto.company.CreateCompanyDto;
import org.example.dto.company.UpdateCompanyDto;
import org.example.dto.employee.CreateEmployeeDto;
import org.example.dto.employee.UpdateEmployeeDto;
import org.example.dto.vehicle.CreateVehicleDto;
import org.example.dto.vehicle.UpdateVehicleDto;
import org.example.util.FileService;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.List;

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

            ///  VEHICLE
            IVehicleService vehicleService = new VehicleService();

            // --- Create a vehicle for company id = 1 ---
            CreateVehicleDto createVehicleDto = new CreateVehicleDto(
                    "CA1234AA",
                    VehicleType.TRUCK,
                    1L);
            vehicleService.create(createVehicleDto);

            UpdateVehicleDto updateVehicleDto = new UpdateVehicleDto(
                    1L,
                    "CA5678BB",
                    VehicleType.BUS);
            vehicleService.update(updateVehicleDto);

            System.out.println("Vehicle operations completed successfully.");

            ///  EMPLOYEE
            IEmployeeService employeeService = new EmployeeService();

            // Create employee in company id=1
            CreateEmployeeDto createEmployeeDto = new CreateEmployeeDto(
                    "John Doe",
                    2500.0,
                    DriverType.REGULAR,
                    1L
            );
            employeeService.create(createEmployeeDto);

            // Update employee with id=1
            UpdateEmployeeDto updateEmployeeDto = new UpdateEmployeeDto(
                    1L,
                    "John Smith",
                    2700.0,
                    DriverType.SPECIAL_CARGO
            );
            employeeService.update(updateEmployeeDto);

            System.out.println("Employee operations completed successfully.");

            ///  CARGO
            ICargoService cargoService = new CargoService();

            CreateCargoDto createCargoDto = new CreateCargoDto(
                    500.0,
                    false,
                    1000.0,
                    "Sofia",
                    "Plovdiv",
                    LocalDateTime.now().plusHours(2),
                    LocalDateTime.now().plusHours(5),
                    CargoType.GOODS,
                    1L,  // vehicleId
                    1L,  // driverId
                    1L,  // clientId
                    1L   // companyId
            );

            cargoService.create(createCargoDto);
            System.out.println("Cargo recorded successfully!");

            /// TASK 6
            boolean paid = clientService.hasClientPaidAllDebts(1L);
            System.out.println("Client has paid all debts: " + paid);

            cargoService.payForCargo(1L);

            paid = clientService.hasClientPaidAllDebts(1L);
            System.out.println("Client has paid all debts: " + paid);

            ///  TASK 7
            ///  a.
            List<Company> companies = companyService.getSortedByNameAndRevenue();
            System.out.println("Companies sorted by name and revenue: ");
            companies.forEach(c -> System.out.println(c.getName() + " " + c.getRevenue()));

            /// b.
            List<Employee> employees = employeeService.getByCompanyIdAndSortByQualificationAndSalary(1L);
            System.out.println("Employees by company id sorted by qualification and salary: ");
            employees.forEach(e -> System.out.println(e.getName() + " " + e.getSalary()));

            ///  c.
            List<Cargo> cargos = cargoService.getByCompanyIdAndSortByDestination(1L);
            System.out.println("Cargos by company Id and sorted by destination: " + paid);
            cargos.forEach(c -> System.out.println(c.getDestination()));

            ///  TASK 8
            FileService fileService = new FileService();
            CargoDto cargoDto = cargoService.getCargoById(1L);
            fileService.saveCargoAsFile(cargoDto);
            System.out.println(fileService.readCargoFromFile(1L));
        }
    }
}
