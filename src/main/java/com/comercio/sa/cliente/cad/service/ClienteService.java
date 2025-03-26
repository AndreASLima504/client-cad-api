package com.comercio.sa.cliente.cad.service;

import com.comercio.sa.cliente.cad.entity.Cliente;
import com.comercio.sa.cliente.cad.repository.ClienteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.catalina.User;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRep;


    public Cliente createCliente(Cliente cliente){
        try{
            if (cliente.getNome() == null){
                throw new RuntimeException("Nome do cliente não deve ser vazio");
            }
            if(cliente.getCpf() == null){
                throw new RuntimeException("CPF do cliente não deve ser vazio");
            }
            return clienteRep.save(cliente);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Cliente> getCliente() {
        try{
            return clienteRep.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Cliente updateClienteById(String id, Cliente newCliente){
//        id = Integer.parseInt(id);
        try{
            // Verifica se o cliente existe
            var clienteExists = clienteRep.findById(Integer.parseInt(id));
            if(!clienteExists.isPresent()){
                throw new RuntimeException("Cliente não encontrado");
            }
                var cliente = clienteExists.get();
                // Valida os novos dados a serem enviados
                validarDados(newCliente);
                // Atualiza o objeto cliente com os dados newCliente
                atualizarCliente(cliente, newCliente);

                return clienteRep.save(cliente);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String deleteCliente(String id){
        // Converte id em int
        int clienteId = Integer.parseInt(id);
        var clienteExists = clienteRep.findById(clienteId);
        if(clienteExists.isEmpty()){
            throw new RuntimeException("Cliente inexistente");
        }

        try{
            var cliente = clienteExists.get();
            clienteRep.deleteById(clienteId);
            return "Cliente deletado: ID" + cliente.getId();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    private void validarDados(Cliente newCliente){
        if(newCliente.getNome() == null || newCliente.getNome().isEmpty()){
            throw new RuntimeException("Nome do cliente não pode ser vazio");
        }
        if(newCliente.getCpf() == null || newCliente.getCpf().isEmpty()){
            throw new RuntimeException("CPF do cliente não pode ser vazio");
        }
        try{
            LocalDate.parse(newCliente.getData_nascimento().toString(), DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception e) {
            throw new RuntimeException("Data de nascimento inválida");
        }
    }
    private Cliente atualizarCliente(Cliente cliente, Cliente newCliente){
        cliente.setNome(newCliente.getNome());
        cliente.setCpf(newCliente.getCpf());
        cliente.setData_nascimento(newCliente.getData_nascimento());
        cliente.setEndereco(newCliente.getEndereco());
        return cliente;
    }


}
