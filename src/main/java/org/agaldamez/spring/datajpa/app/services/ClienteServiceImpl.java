package org.agaldamez.spring.datajpa.app.services;

import org.agaldamez.spring.datajpa.app.models.entity.Cliente;
import org.agaldamez.spring.datajpa.app.repositories.ClienteRepository;
import org.agaldamez.spring.datajpa.app.repositories.SpringDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private SpringDataRepository repository;

    @Override
    @Transactional
    public void saveClient (Cliente cliente) {
        repository.save(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> getAllClients () {
        return (List<Cliente>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente getClientById (Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void removeClientById (Long id) {
        repository.deleteById(id);
    }
}
