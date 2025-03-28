package com.comercio.sa.cliente.cad.controller;

import com.comercio.sa.cliente.cad.entity.Cliente;
import com.comercio.sa.cliente.cad.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/cliente")
public class ClienteController {

    // SpringBoot faz a injeção de dependência da classe ClientService
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> listClientes(){
        var clientes = clienteService.getCliente();
        return ResponseEntity.ok(clientes);
    }

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente){
        return ResponseEntity.ok(clienteService.createCliente(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateClienteById(@PathVariable("id") String id,
                                                     @RequestBody Cliente cliente){
        return ResponseEntity.ok(clienteService.updateClienteById(id, cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable("id") String id){
        return ResponseEntity.ok(clienteService.deleteCliente(id));
    }

}
