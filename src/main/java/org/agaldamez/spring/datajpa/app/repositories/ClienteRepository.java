package org.agaldamez.spring.datajpa.app.repositories;

import org.agaldamez.spring.datajpa.app.models.entity.Cliente;

import java.util.List;

public interface ClienteRepository {

    void save(Cliente cliente);

    List<Cliente> findAll();

    Cliente findById(Long id);

    void deleteById(Long id);

}
