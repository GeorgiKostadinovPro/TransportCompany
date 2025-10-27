package org.example;

import org.example.controller.*;
import org.example.controller.contracts.*;

import java.util.Arrays;
import java.util.Scanner;

import static org.example.common.OutputMessages.AVAILABLE_COMMANDS;

public class Engine implements Runnable {
    private final Scanner scanner;

    private final ICompanyController companyController;
    private final IEmployeeController employeeController;
    private final IClientController clientController;
    private final IVehicleController vehicleController;
    private final ICargoController cargoController;

    public Engine() {
        this.scanner = new Scanner(System.in);

        this.companyController = new CompanyController();
        this.employeeController = new EmployeeController();
        this.clientController = new ClientController();
        this.vehicleController = new VehicleController();
        this.cargoController = new CargoController();
    }

    @Override
    public void run() {
        printWelcomeScreen();

        while (true) {
            System.out.print("> ");
            String result;

            try {
                result = processInput();

                if (result.equals("Stop")) {
                    System.out.println("Application terminated.");
                    break;
                }
            } catch (Exception e) {
                result = e.getMessage();
            }

            System.out.println(result);
        }
    }

    private void printWelcomeScreen() {
        StringBuilder sb = new StringBuilder();
        System.out.println("=== Welcome to Transport Company Management System ===");
        System.out.println("Type a command (or 'Stop' to exit).");
        sb.append("Available Commands:\n");

        for (int i = 0; i < AVAILABLE_COMMANDS.length; i++) {
            sb.append(String.format("%2d. %s%n", i + 1, AVAILABLE_COMMANDS[i]));
        }

        System.out.println(sb);
    }

    private String processInput() {
        String input = this.scanner.nextLine().trim();
        if (input.isEmpty()) return null;

        String[] tokens = input.split("\\s+");
        String command = tokens[0];
        // CMD PARAM1 PARAM2 PARAM3 => Clone 1...len => PARAM1...PARAMn
        String[] args = Arrays.copyOfRange(tokens, 1, tokens.length);

        return switch (command) {
            // === COMPANY COMMANDS ===
            case "CreateCompany" -> this.companyController.create(args);
            case "UpdateCompany" -> this.companyController.update(args);
            case "DeleteCompany" -> this.companyController.delete(args);
            case "GetCompaniesSorted" -> this.companyController.getSortedByNameAndRevenue();
            case "GetCompanyRevenueForPeriod" -> this.companyController.getCompanyRevenueForPeriod(args);

            // === EMPLOYEE COMMANDS ===
            case "CreateEmployee" -> this.employeeController.create(args);
            case "UpdateEmployee" -> this.employeeController.update(args);
            case "DeleteEmployee" -> this.employeeController.delete(args);
            case "GetEmployeesByCompanySorted" -> this.employeeController.getByCompanyIdAndSortByQualificationAndSalary(args);
            case "GetDriversWithCargoCountByCompany" -> this.employeeController.getDriversWithCargoCountByCompanyId(args);
            case "GetDriverRevenueByCompany" -> this.employeeController.getDriverRevenueByCompanyId(args);

            // === CLIENT COMMANDS ===
            case "CreateClient" -> this.clientController.create(args);
            case "UpdateClient" -> this.clientController.update(args);
            case "DeleteClient" -> this.clientController.delete(args);
            case "HasClientPaidAllDebts" -> this.clientController.hasClientPaidAllDebts(args);

            // === VEHICLE COMMANDS ===
            case "CreateVehicle" -> this.vehicleController.create(args);
            case "UpdateVehicle" -> this.vehicleController.update(args);
            case "DeleteVehicle" -> this.vehicleController.delete(args);

            // === CARGO COMMANDS ===
            case "CreateCargo" -> this.cargoController.create(args);
            case "PayForCargo" -> this.cargoController.payForCargo(args);
            case "SaveCargoToFile" -> this.cargoController.saveCargoToFile(args);
            case "ReadCargoFromFile" -> this.cargoController.readCargoFromFile(args);
            case "GetCargoById" -> this.cargoController.getById(args);
            case "GetCargoCountByCompany" -> this.cargoController.getCountByCompany(args);
            case "GetCargosByCompanySorted" -> this.cargoController.getByCompanyAndSortByDestination(args);
            case "GetTotalCargoRevenueByCompany" -> this.cargoController.getTotalRevenueByCompany(args);

            // === GENERAL ===
            case "Stop" -> "Stop";
            default -> "Unknown command: " + command;
        };
    }
}
