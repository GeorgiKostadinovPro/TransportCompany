package org.example.controller.contracts;

public interface IClientController {
    String create(String[] args);
    String update(String[] args);
    String delete(String[] args);

    String hasClientPaidAllDebts(String[] args);
}
