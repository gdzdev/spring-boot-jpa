package org.agaldamez.spring.datajpa.app.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.agaldamez.spring.datajpa.app.models.entity.Cliente;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save (Cliente cliente) {
        if (cliente.getId() != null && cliente.getId() > 0) {
            entityManager.merge(cliente);
        } else {
            entityManager.persist(cliente);
        }
    }

    @Override
    public List<Cliente> findAll () {
        return entityManager
                .createQuery("Select c From Cliente c", Cliente.class)
                .getResultList();
    }

    @Override
    public Cliente findById (Long id) {
        return entityManager.find(Cliente.class, id);
    }

    @Override
    public void deleteById (Long id) {
        entityManager.remove(findById(id));
    }
}
