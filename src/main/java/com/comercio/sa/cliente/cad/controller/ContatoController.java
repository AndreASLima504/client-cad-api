package com.comercio.sa.cliente.cad.controller;

import com.comercio.sa.cliente.cad.dto.ContatoDTO;
import com.comercio.sa.cliente.cad.entity.Contato;
import com.comercio.sa.cliente.cad.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/contato")
public class ContatoController {
    @Autowired
    ContatoService contatoService;

    @PostMapping
    public ResponseEntity<Contato> createContato(@RequestBody ContatoDTO contato){
        return ResponseEntity.ok(contatoService.createContato(contato));
    }

    @GetMapping
    public ResponseEntity<List<Contato>> listContatos(){
        return ResponseEntity.ok(contatoService.listContatos());
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<List<Contato>> listContatosByClienteId(@PathVariable("clienteId") String clienteId){
        return ResponseEntity.ok(contatoService.listContatosByClienteId(clienteId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContato(@PathVariable("id") String id){
        return ResponseEntity.ok(contatoService.deleteContato(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contato> updateContato(@PathVariable("id") String id,
                                                 @RequestBody ContatoDTO contatoDTO){
        return ResponseEntity.ok(contatoService.updateContatoById(id, contatoDTO));
    }
}
