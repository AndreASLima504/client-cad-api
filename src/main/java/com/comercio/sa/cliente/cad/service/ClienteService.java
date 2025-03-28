package com.comercio.sa.cliente.cad.service;

import com.comercio.sa.cliente.cad.entity.Cliente;
import com.comercio.sa.cliente.cad.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
            validarDados(cliente);
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
        // Validação do nome
        if(newCliente.getNome() == null || newCliente.getNome().isEmpty()){
            throw new RuntimeException("Nome do cliente não pode ser vazio");
        }

        // Valida se o CPF não é nulo ou vazio
        var cpf = newCliente.getCpf();
        if(cpf == null || cpf.isEmpty()){
            throw new RuntimeException("CPF do cliente não pode ser vazio");
        }
        // Formata o CPF para o formato padrão
        newCliente.setCpf(formatarCpf(cpf));

        try{
            LocalDate.parse(newCliente.getData_nascimento().toString(), DateTimeFormatter.ISO_LOCAL_DATE);
            if(!newCliente.getData_nascimento().isBefore(LocalDate.now())){
                throw new RuntimeException(" Uma data futura não pode ser utilizada");
            };
        } catch (Exception e) {
            throw new RuntimeException("Data de nascimento inválida. Utilize o formato AAAA-MM-DD" + e.getMessage());
        }
    }

    private String formatarCpf(String cpf){
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se tem exatamente 11 dígitos
        if (!cpf.matches("\\d{11}")) {
            throw new RuntimeException("CPF inválido. Deve conter exatamente 11 dígitos.");
        }
        return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    private Cliente atualizarCliente(Cliente cliente, Cliente newCliente){
        cliente.setNome(newCliente.getNome());
        cliente.setCpf(formatarCpf(newCliente.getCpf()));
        cliente.setCpf(newCliente.getCpf());
        cliente.setData_nascimento(newCliente.getData_nascimento());
        cliente.setEndereco(newCliente.getEndereco());
        return cliente;
    }


}
