package org.example.common;

public class OutputMessages {
    ///  COMPANY
    public static final String COMPANY_CREATED_SUCCESSFULLY = "Company created successfully.";
    public static final String COMPANY_UPDATED_SUCCESSFULLY = "Company updated successfully.";
    public static final String COMPANY_DELETED_SUCCESSFULLY = "Company deleted successfully.";

    public static final String COMPANY_NAME_AND_REVENUE = "Company: %s | Revenue: $%.2f";
    public static final String COMPANY_REVENUE_FOR_PERIOD = "Company ID #%s | revenue for the period: $%.2f";

    ///  CLIENT
    public static final String CLIENT_CREATED_SUCCESSFULLY = "Company created successfully.";
    public static final String CLIENT_UPDATED_SUCCESSFULLY = "Company updated successfully.";
    public static final String CLIENT_DELETED_SUCCESSFULLY = "Company deleted successfully.";

    public static final String CLIENT_PAID_DEBTS = "Client has paid all debts.";
    public static final String CLIENT_NOT_PAID_DEBTS = "Client still has unpaid debts.";

    ///   VEHICLE
    public static final String VEHICLE_CREATED_SUCCESSFULLY = "Vehicle created successfully.";
    public static final String VEHICLE_UPDATED_SUCCESSFULLY = "Vehicle updated successfully.";
    public static final String VEHICLE_DELETED_SUCCESSFULLY = "Vehicle deleted successfully.";

    ///   EMPLOYEE
    public static final String EMPLOYEE_CREATED_SUCCESSFULLY = "Employee created successfully.";
    public static final String EMPLOYEE_UPDATED_SUCCESSFULLY = "Employee updated successfully.";
    public static final String EMPLOYEE_DELETED_SUCCESSFULLY = "Employee deleted successfully.";

    public static final String EMPLOYEE_WITH_NAME_TYPE_PRICE = "%s | %s | %.2f";
    public static final String EMPLOYEE_WITH_CARGO_COUNT = "%s | %d cargo(s)";
    public static final String EMPLOYEE_WITH_TOTAL_REVENUE = "%s | $%.2f";

    ///   CARGO
    public static final String CARGO_CREATED_SUCCESSFULLY = "Cargo created successfully.";
    public static final String CARGO_PAYMENT_COMPLETED = "Cargo payment marked as completed.";

    public static final String CARGO_WITH_DESTINATION = "Cargo #%s | destination %s";
    public static final String CARGO_COUNT_BY_COMPANY = "Total cargos for company #%s: %d cargo(s)";
    public static final String TOTAL_CARGO_REVENUE_BY_COMPANY = "Total revenue for company #%d: $%.f2";

    ///   ENGINE
    public static final String[] AVAILABLE_COMMANDS = {
            // === COMPANY ===
            "CreateCompany {name} {address} {foundationDate}",
            "UpdateCompany {id} {name} {address}",
            "DeleteCompany {id}",
            "GetCompaniesSorted",
            "GetCompanyRevenueForPeriod {companyId} {startDate} {endDate}",

            // === EMPLOYEE ===
            "CreateEmployee {name} {salary} {qualification} {companyId}",
            "UpdateEmployee {id} {name} {salary} {qualification}",
            "DeleteEmployee {id}",
            "GetEmployeesByCompanySorted {companyId}",
            "GetDriversWithCargoCountByCompany {companyId}",
            "GetDriverRevenueByCompany {companyId}",

            // === CLIENT ===
            "CreateClient {name} {companyId}",
            "UpdateClient {id} {name}",
            "DeleteClient {id}",
            "HasClientPaidAllDebts {clientId}",

            // === VEHICLE ===
            "CreateVehicle {registrationNumber} {type} {companyId}",
            "UpdateVehicle {id} {registrationNumber} {type}",
            "DeleteVehicle {id}",

            // === CARGO ===
            "CreateCargo {price} {isPaid} {weight} {origin} {destination} {departureDate} {arrivalDate} {type} {vehicleId} {driverId} {clientId} {companyId}",
            "PayForCargo {cargoId}",
            "GetCargoById {cargoId}",
            "GetCargosByCompany {companyId}",
            "GetCargoCountByCompany {companyId}",
            "GetTotalCargoRevenueByCompany {companyId}",

            // === GENERAL ===
            "Stop"
    };
}
