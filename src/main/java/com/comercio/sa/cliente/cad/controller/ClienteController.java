package com.comercio.sa.cliente.cad.controller;

import com.comercio.sa.cliente.cad.entity.Cliente;
import com.comercio.sa.cliente.cad.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/client")
public class ClienteController {

    // SpringBoot faz a injeção de dependência da classe ClientService
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> listClients(){
        var clientes = clienteService.getCliente();
        return ResponseEntity.ok(clientes);
    }

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente){
        return ResponseEntity.ok(clienteService.createCliente(cliente));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Cliente> updateClienteById(@PathVariable("userId") String id,
                                                     @RequestBody Cliente cliente){
        return ResponseEntity.ok(clienteService.updateClienteById(id, cliente));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteCliente(@PathVariable("userId") String id){
        return ResponseEntity.ok(clienteService.deleteCliente(id));
    }

}
