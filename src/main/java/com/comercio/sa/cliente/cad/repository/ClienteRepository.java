package com.comercio.sa.cliente.cad.repository;

import com.comercio.sa.cliente.cad.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
