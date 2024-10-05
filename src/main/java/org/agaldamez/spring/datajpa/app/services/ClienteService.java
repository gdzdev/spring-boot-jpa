package org.agaldamez.spring.datajpa.app.services;

import org.agaldamez.spring.datajpa.app.models.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    void saveClient (Cliente cliente);

    List<Cliente> getAllClients ();

    Cliente getClientById (Long id);

    void removeClientById (Long id);
}
