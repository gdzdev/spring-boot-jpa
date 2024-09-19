package org.agaldamez.spring.datajpa.app.repositories;

import org.agaldamez.spring.datajpa.app.models.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface SpringDataRepository extends CrudRepository<Cliente, Long> {
}
