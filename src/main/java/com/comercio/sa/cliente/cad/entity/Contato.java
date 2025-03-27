package com.comercio.sa.cliente.cad.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "contato")

public class Contato {

    public Contato() {
    }

    public Contato(Cliente cliente, String tipoContato, String valor, String observacao) {
        this.cliente = cliente;
        this.tipoContato = tipoContato;
        this.valor = valor;
        this.observacao = observacao;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name="cliente_id", nullable = false, updatable = true)
    Cliente cliente;

    @JsonProperty("tipo_contato")
    @Column(name = "tipo_contato", length = 50)
    String tipoContato;

    @Column(name = "valor", length = 50)
    String valor;

    @Column(name = "observacao")
    String observacao;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(String tipoContato) {
        this.tipoContato = tipoContato;
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
