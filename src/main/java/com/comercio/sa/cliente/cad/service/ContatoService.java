package com.comercio.sa.cliente.cad.service;

import com.comercio.sa.cliente.cad.ContatoDTO;
import com.comercio.sa.cliente.cad.entity.Cliente;
import com.comercio.sa.cliente.cad.entity.Contato;
import com.comercio.sa.cliente.cad.repository.ClienteRepository;
import com.comercio.sa.cliente.cad.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ContatoService {
    @Autowired
    ContatoRepository contatoRep;
    @Autowired
    ClienteRepository clienteRep;

    public Contato createContato(ContatoDTO contatoDTO){
        try{
            var cliente = clienteRep.findById(contatoDTO.getCliente_id()).orElseThrow();
            Contato contato = new Contato(cliente, contatoDTO.getTipo_Contato(),
                    contatoDTO.getValor(), contatoDTO.getObservacao());

            validarDados(contato);
            return contatoRep.save(contato);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Contato> listContatos() {
        try{
            return contatoRep.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Contato> listContatosByClienteId(String clienteId) {
        try{
            var cliente = clienteRep.findById(Integer.parseInt(clienteId)).orElseThrow();
            return contatoRep.findByCliente(cliente);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


//
    public Contato updateContatoById(String id, ContatoDTO contatoDTO){
        try{
            // Verifica se o contato existe
            var contatoExists = contatoRep.findById(Integer.parseInt(id));
            if(contatoExists.isEmpty()){
                throw new RuntimeException("Contato não encontrado");
            }
            // Verifica se id do cliente é válido e coloca na variável
            var cliente = clienteRep.findById(contatoDTO.getCliente_id()).orElseThrow();

            var contato = contatoExists.get();
            // Atualiza o objeto contato com os dados contatoDTO
            contato.setTipoContato(contatoDTO.getTipo_Contato());
            contato.setCliente(cliente);
            contato.setValor(contatoDTO.getValor());
            contato.setObservacao(contatoDTO.getObservacao());

            validarDados(contato);
            return contatoRep.save(contato);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//
    public String deleteContato(String id){
        // Converte id em int
        int contatoId = Integer.parseInt(id);
        var contato = contatoRep.findById(contatoId);
        if(contato.isEmpty()){
            throw new RuntimeException("Contato inexistente");
        }

        try{
            contatoRep.deleteById(contatoId);
            return "Contato deletado: ID" + contato.get().getId();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    private void validarDados(Contato contato){
        var cliente = contato.getCliente();
        var tipoContato = contato.getTipoContato();
        var valor = contato.getValor();

        if (cliente == null){
            throw new RuntimeException("Cliente não pode ser vazio");
        }
        if (tipoContato == null || tipoContato.isEmpty()){
            throw new RuntimeException("Tipo de contato não pode ser vazio");
        }
        if(valor == null || valor.isEmpty()){
            throw new RuntimeException("Valor do contato (e-mail, telefone, etc.) não pode ser vazio");
        }
    }
}
