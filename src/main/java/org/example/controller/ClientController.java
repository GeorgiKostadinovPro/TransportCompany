package org.example.controller;

import org.example.controller.contracts.IClientController;
import org.example.core.contracts.IClientService;
import org.example.dto.client.CreateClientDto;
import org.example.dto.client.UpdateClientDto;

import static org.example.common.OutputMessages.CLIENT_NOT_PAID_DEBTS;
import static org.example.common.OutputMessages.CLIENT_PAID_DEBTS;

public class ClientController implements IClientController {
    private final IClientService clientService;

    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public String create(String[] args) {
        String name = args[0];
        String email = args[1];
        String phone = args[2];
        long companyId = Long.parseLong(args[3]);

        clientService.create(new CreateClientDto(name, email, phone, false, companyId));

        return "Client created successfully.";
    }

    @Override
    public String update(String[] args) {
        long id = Long.parseLong(args[0]);
        String name = args[1];
        String email = args[2];
        String phone = args[3];
        boolean hasPaid = Boolean.parseBoolean(args[4]);

        clientService.update(new UpdateClientDto(id, name, email, phone, hasPaid));

        return "Client updated successfully.";
    }

    @Override
    public String delete(String[] args) {
        long id = Long.parseLong(args[0]);

        clientService.delete(id);

        return "Client deleted successfully.";
    }

    @Override
    public String hasClientPaidAllDebts(String[] args) {
        long clientId = Long.parseLong(args[0]);

        boolean hasPaid = clientService.hasClientPaidAllDebts(clientId);

        return hasPaid ? CLIENT_PAID_DEBTS : CLIENT_NOT_PAID_DEBTS;
    }
}
