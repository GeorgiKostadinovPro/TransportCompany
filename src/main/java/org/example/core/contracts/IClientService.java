package org.example.core.contracts;

import org.example.dto.client.CreateClientDto;
import org.example.dto.client.UpdateClientDto;

public interface IClientService {
    void create(CreateClientDto dto);
    void update(UpdateClientDto dto);
    void delete(long id);
}
