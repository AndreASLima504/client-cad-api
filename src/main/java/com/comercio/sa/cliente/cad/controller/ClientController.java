package com.comercio.sa.cliente.cad.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {
    @GetMapping
    public String listClients(){
        return "Hello World";
    }
}
