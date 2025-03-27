package com.comercio.sa.cliente.cad.repository;

import com.comercio.sa.cliente.cad.entity.Cliente;
import com.comercio.sa.cliente.cad.entity.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Integer> {
    List<Contato> findByCliente(Cliente cliente);
}
