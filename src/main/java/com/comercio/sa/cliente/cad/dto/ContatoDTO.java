package com.comercio.sa.cliente.cad.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContatoDTO {
    Integer id;
    Integer cliente_id;
    @JsonProperty("tipo_contato")
    String tipo_contato;
    String valor;
    String observacao;
    String nome_cliente;

    public ContatoDTO(Integer id, Integer cliente_id, String tipo_contato, String valor, String observacao, String nome_cliente) {
        this.id = id;
        this.cliente_id = cliente_id;
        this.tipo_contato = tipo_contato;
        this.valor = valor;
        this.observacao = observacao;
        this.nome_cliente = nome_cliente;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Integer cliente) {
        this.cliente_id = cliente;
    }

    public String getTipo_Contato() {
        return tipo_contato;
    }

    public void setTipo_Contato(String tipo_contato) {
        this.tipo_contato = tipo_contato;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
